package com.Cybersoft.uniclub09.controller;

import com.Cybersoft.uniclub09.payload.request.ProductRequest;
import com.Cybersoft.uniclub09.payload.respone.BaseRespone;
import com.Cybersoft.uniclub09.services.FileStorageService;
import com.Cybersoft.uniclub09.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductServices productServices;

    @PostMapping("")
    public ResponseEntity<?> insertProduct(ProductRequest productRequest) {
        productServices.insertProduct(productRequest);
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(200);
        baseRespone.setMessage("Insert product successfully");
        baseRespone.setData("Product data here");
        return ResponseEntity.ok(baseRespone);
    }

//    @PostMapping("")
//    public ResponseEntity<?> insertProduct(@RequestParam("file") MultipartFile file) {
//        productServices.insertProduct(file);
//        BaseRespone baseRespone = new BaseRespone();
//        baseRespone.setCode(200);
//        baseRespone.setMessage("Insert product successfully");
//        baseRespone.setData("Product data here");
//        return ResponseEntity.ok(baseRespone);
//    }

    @GetMapping()
    public ResponseEntity<?> getAllProduct() {
        BaseRespone baseRespone = new BaseRespone();
        baseRespone.setCode(200);
        baseRespone.setMessage("");
        baseRespone.setData(productServices.getAllProduct());
        return ResponseEntity.ok(baseRespone);
    }
}
