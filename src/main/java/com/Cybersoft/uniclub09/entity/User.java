package com.Cybersoft.uniclub09.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
@Entity(name = "users")
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private LocalDateTime createDate;
}
