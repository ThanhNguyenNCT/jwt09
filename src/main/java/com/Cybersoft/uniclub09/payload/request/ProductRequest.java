package com.Cybersoft.uniclub09.payload.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ProductRequest {
    private MultipartFile file;
    private String name;
    private String description;
    private double basePrice;
    private int idColor;
    private int idSize;
    private int idProduct;
    private int quantity;
    private double price;
}
