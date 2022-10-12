package com.redhat.demo.common.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
@ToString
public class Greeting implements Serializable {

    Long id;
    String userFrom;
    String userTo;
    String description;
    Date creationDate;
    int likes;

}
