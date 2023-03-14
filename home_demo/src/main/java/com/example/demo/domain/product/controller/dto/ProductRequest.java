package com.example.demo.domain.product.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductRequest {
//RequestProductInfo를 사용하면서 이건이제 안쓰는듯?
    final private String productName;
    final private String writer;
    final private String content;
    final private Integer price;
}
