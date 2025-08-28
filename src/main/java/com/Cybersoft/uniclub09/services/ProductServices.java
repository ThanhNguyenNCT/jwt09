package com.Cybersoft.uniclub09.services;

import com.Cybersoft.uniclub09.dto.ProductDTO;
import com.Cybersoft.uniclub09.payload.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductServices {
    void insertProduct(ProductRequest productRequest);
//    public boolean insertProduct(MultipartFile file);
    List<ProductDTO> getAllProduct();

}
