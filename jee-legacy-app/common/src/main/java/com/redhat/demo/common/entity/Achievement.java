package com.redhat.demo.common.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Achievement implements Serializable {

    @Id
    private Long id;
    private String owner;
    private String description;
    private Date creationDate;

    public Achievement() {
    }

    public Achievement(Long id, String owner, String description, Date creationDate) {
        this.id = id;
        this.owner = owner;
        this.description = description;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    private void setOwner(String owner) {
        this.owner = owner;
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

    @Override
    public String toString() {
        return "Kudo{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Achievement achievement = new Achievement();

        public Builder id(Long id) {
            achievement.setId(id);
            return this;
        }

        public Builder owner(String owner) {
            achievement.setOwner(owner);
            return this;
        }

        public Builder description(String description) {
            achievement.setDescription(description);
            return this;
        }

        public Builder creationDate(Date creationDate) {
            achievement.setCreationDate(creationDate);
            return this;
        }

        public Achievement build() {
            return achievement;
        }

    }


}
