package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.dto.*;
import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
//    void register(ProductRequest productRequest);//이미지올리기 전 코드
    void register(List<MultipartFile> imageFileList,
              RequestProductInfo productRequest);

//    List<Product> list();//이미지올리기 전 코드
    List<ProductListResponse> list();

//    Product read(Long productId);
    ProductReadResponse read(Long productId);

    void remove(Long productId);

    Product modify(Long productId, ProductRequest productRequest);
//    List<ImageResource> findProductImage(Long productId);
    List<ImageResourceResponse> findProductImage(Long productId);
}
