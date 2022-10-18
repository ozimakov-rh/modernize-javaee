package com.redhat.demo.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.redhat.demo.common.entity.Kudo;

import javax.ejb.Singleton;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Singleton(name = "file_store")
public class FileSystemKudoStoreImpl implements KudoStore {

    private final File dataFile;
    private final Gson gson = new Gson();

    public FileSystemKudoStoreImpl() {
        try {
            dataFile = File.createTempFile("kudo", ".json");
            System.out.println(" > " + dataFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized <T> T doPersisted(Function<List<Kudo>,T> fun) {
        try {
            // load list from file
            List<Kudo> dataList = gson.fromJson(new FileReader(dataFile), new TypeToken<ArrayList<Kudo>>(){}.getType());

            if (dataList == null) {
                dataList = new ArrayList<>();
            }

            // do stuff
            T result = fun.apply(dataList);

            // save the list back
            String jsonData = gson.toJson(dataList);
            Files.write(dataFile.toPath(), jsonData.getBytes(StandardCharsets.UTF_8));

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Kudo kudo) {
        doPersisted(kudos -> {
            kudos.add(kudo);
            return null;
        });
    }

    @Override
    public Stream<Kudo> stream() {
        return doPersisted(kudos -> kudos.stream());
    }

    @Override
    public List<Kudo> list() {
        return doPersisted(kudos -> kudos);
    }

    @Override
    public void deleteById(Long id) {
        doPersisted(kudos -> kudos.removeIf(kudo -> kudo.getId().equals(id)));
    }
}
