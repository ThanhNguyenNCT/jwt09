package com.Cybersoft.uniclub09.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "variant")
@Data
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sku;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    private String images;
    private int quantity;
    private double price;
    private LocalDateTime createDate;
}
