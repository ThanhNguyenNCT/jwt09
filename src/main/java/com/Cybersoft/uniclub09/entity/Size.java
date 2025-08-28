package com.Cybersoft.uniclub09.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "size")
@Data
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "size")
    private List<Variant> variants;
}
