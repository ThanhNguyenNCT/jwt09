package com.Cybersoft.uniclub09.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private Role role;

    @PrePersist
    public void prePersist() {
        if (this.createDate == null) {
            this.createDate = LocalDateTime.now();
        }

        if (this.id == null) {
            this.id = java.util.UUID.randomUUID().toString();
        }
    }
}
