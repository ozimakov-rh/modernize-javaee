package com.redhat.demo.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Kudo implements Serializable {

    @Id
    private Long id;
    private String userFrom;
    private String userTo;
    private String description;
    private Date creationDate;
    private int likes;

    public Kudo() {
    }

    public Kudo(Long id, String userFrom, String userTo, String description, Date creationDate, int likes) {
        this.id = id;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.description = description;
        this.creationDate = creationDate;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getUserFrom() {
        return userFrom;
    }

    private void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    private void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getLikes() {
        return likes;
    }

    private void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Kudo{" +
                "id=" + id +
                ", userFrom='" + userFrom + '\'' +
                ", userTo='" + userTo + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", likes=" + likes +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Kudo kudo = new Kudo();

        public Builder id(Long id) {
            kudo.setId(id);
            return this;
        }

        public Builder userFrom(String userFrom) {
            kudo.setUserFrom(userFrom);
            return this;
        }

        public Builder userTo(String userTo) {
            kudo.setUserTo(userTo);
            return this;
        }

        public Builder description(String description) {
            kudo.setDescription(description);
            return this;
        }

        public Builder creationDate(Date creationDate) {
            kudo.setCreationDate(creationDate);
            return this;
        }

        public Builder likes(int likes) {
            kudo.setLikes(likes);
            return this;
        }

        public Kudo build() {
            return kudo;
        }

    }


}
