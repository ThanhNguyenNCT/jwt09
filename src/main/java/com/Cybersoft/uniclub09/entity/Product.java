package com.Cybersoft.uniclub09.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String information;
    private double price;
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "product")
    private List<Variant> variants;
}
