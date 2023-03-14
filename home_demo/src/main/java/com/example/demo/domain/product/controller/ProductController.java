package com.example.demo.domain.product.controller;

import com.example.demo.domain.product.controller.dto.*;
import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class ProductController {

    final private ProductService productService;

/*//이미지있기 전 코드
        @PostMapping("/register")
    public void productRegister(@RequestBody ProductRequest productRequest) {
        log.info("productRegister()");

        productService.register(productRequest);
    }

 */
    //이미지 업로드 후 코드
    @PostMapping(value = "/register",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void productRegister(//@RequestPart(value = "fileList") List<MultipartFile> fileList,
                                @RequestPart(value = "imageFileList") List<MultipartFile> imageFileList,
                                @RequestPart(value = "productInfo") RequestProductInfo productRequest) {
        //formData의 파일이랑 글자정보를 받아온다
        log.info("productRegister()");
        productService.register(imageFileList, productRequest);
    }

    @GetMapping("/list")
    public List<ProductListResponse> productList () {
        log.info("boardList()");

        return productService.list();
    }

    @GetMapping("/{productId}")
    public ProductReadResponse productRead(@PathVariable("productId") Long productId) {
        log.info("productRead()");

        return productService.read(productId);
    }

    @DeleteMapping("/{productId}")
    public void productRemove(@PathVariable("productId") Long productId) {
        log.info("productRemove()");

        productService.remove(productId);
    }

    @PutMapping("/{productId}")
    public Product productModify(@PathVariable("productId") Long productId,
                                 @RequestBody ProductRequest productRequest) {

        log.info("productModify(): " + productRequest + "id: " + productId);

        return productService.modify(productId, productRequest);
    }
    @GetMapping("/imageList/{productId}")
//    public List<ImageResource> readProductImageResource(
    public List<ImageResourceResponse> readProductImageResource(
            @PathVariable("productId") Long productId) {

        log.info("readProductImageResource(): " + productId);

        return productService.findProductImage(productId);
    }
}
