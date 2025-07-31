package com.Cybersoft.uniclub09.controller;

import com.Cybersoft.uniclub09.payload.respone.BaseRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping("")
    public ResponseEntity<?> insertProduct() {
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(200);
        baseRespone.setMessage("Insert product successfully");
        baseRespone.setData("Product data here");
        return ResponseEntity.ok(baseRespone);
    }
}
