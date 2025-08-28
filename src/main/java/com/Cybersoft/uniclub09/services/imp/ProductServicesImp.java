package com.Cybersoft.uniclub09.services.imp;

import com.Cybersoft.uniclub09.dto.ProductDTO;
import com.Cybersoft.uniclub09.entity.Color;
import com.Cybersoft.uniclub09.entity.Product;
import com.Cybersoft.uniclub09.entity.Size;
import com.Cybersoft.uniclub09.entity.Variant;
import com.Cybersoft.uniclub09.payload.request.ProductRequest;
import com.Cybersoft.uniclub09.repository.ProductRepository;
import com.Cybersoft.uniclub09.repository.VariantRepository;
import com.Cybersoft.uniclub09.services.FileStorageService;
import com.Cybersoft.uniclub09.services.ProductServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServicesImp implements ProductServices {
    @Autowired
    private FileStorageService fileStorageServices;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Transactional//Mặc định xử lý RuntimeException
    //Chỉ dùng khi thao tác với nhiều bảng
    //Tất cả các thao tác trong hàm bên dưới thành công thì mới commit vào database
    //Nếu có lỗi xảy ra thì sẽ rollback lại toàn bộ các thao tác
    //Chỉ dùng khi cần thiết vì nó sẽ làm giảm hiệu năng của ứng dụng    @Override
    public void insertProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getBasePrice());

        Product productInserted = productRepository.save(product);

        Variant variant = new Variant();
        variant.setProduct(productInserted);

        Color color = new Color();
        color.setId(productRequest.getIdColor());
        variant.setColor(color);

        Size size = new Size();
        size.setId(productRequest.getIdSize());
        variant.setSize(size);

        fileStorageServices.save(productRequest.getFile());
        variant.setImages(productRequest.getFile().getOriginalFilename());

        variant.setQuantity(productRequest.getQuantity());
        variant.setPrice(productRequest.getPrice());

        variantRepository.save(variant);
    }
    @Cacheable("products")
    @Override
    public List<ProductDTO> getAllProduct() {
        System.out.println("Check cache");
        return productRepository.findAll().stream().map(item -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setPrice(item.getPrice());
            dto.setLink("/files/" + item.getVariants().getFirst().getImages());
            return dto;
        }).toList();

    }

//    @Override
//    public boolean insertProduct(MultipartFile file) {
//        fileStorageServices.save(file);
//        return true;
//    }

}
