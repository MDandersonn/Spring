package com.example.demo.domain.product.service;

import com.example.demo.domain.product.controller.dto.*;
import com.example.demo.domain.product.controller.dto.ProductRequest;
import com.example.demo.domain.product.entity.ImageResource;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repository.ImageResourceRepository;
import com.example.demo.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import javax.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;
    final private ImageResourceRepository imageResourceRepository;

    /*//이미지 사용 전 코드
    @Override
    public void register(ProductRequest productRequest) {
        Product product = new Product();

        product.setProductName(productRequest.getProductName());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);
    }
     */


    @Transactional
    public void register(List<MultipartFile> imageFileList, RequestProductInfo productRequest) {
        log.info("글자 출력: " + productRequest);
        List<ImageResource> imageResourceList = new ArrayList<>();
        final String fixedStringPath = "../../Vue.js/home_frontend/src/assets/uploadImgs/";
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setWriter(productRequest.getWriter());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        try {
            for (MultipartFile multipartFile : imageFileList) {//이미지 파일리스트중에서 각각 한 이미지를 뽑아서
                log.info("requestFileUploadWithText() - filename: " + multipartFile.getOriginalFilename());
                String fullPath = fixedStringPath + multipartFile.getOriginalFilename();//파일경로를 지정해주고
                FileOutputStream writer = new FileOutputStream(
                        fixedStringPath + multipartFile.getOriginalFilename()
                );
                writer.write(multipartFile.getBytes());//경로에 이미지 저장을한다.
                writer.close();

                ImageResource imageResource = new ImageResource(multipartFile.getOriginalFilename());
                //아이디랑 이미지이름을 설정된 하나의 이미지 row를 만들고
                imageResourceList.add(imageResource);//이미지리스트에넣고
                product.setImageResource(imageResource);//이미지리소스리스트에 누적 추가.
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productRepository.save(product);

        /*
        for (ImageResource imageResource: imageResourceList) {
            imageResourceRepository.save(imageResource);
        }

         */
        //for문돌떄마다 레포지토리와 연결되면 낭비. 한번에 하는게 좋음. 그래서 아래코드로 교체.
        imageResourceRepository.saveAll(imageResourceList);
    }

    /*//이미지 사용 전 코드
    @Override
    public List<Product> list() {
        return productRepository.findAll();
    }
    */
    @Override

    public List<ProductListResponse> list() {

        List<Product> productList = productRepository.findAll(Sort.by(Sort.Direction.DESC, "productId")); //상품 전체 다 검색
        List<ProductListResponse> productResponseList = new ArrayList<>();//응답객체리스트  만들어두고

        for (Product product : productList) {//각 상품마다
            //필요한 컬럼만 뽑아서 응답객체를 만들어서 응답객체리스트에 담아줌
            productResponseList.add(new ProductListResponse(
                    product.getProductId(), product.getProductName(),
                    product.getWriter(), product.getRegDate()
            ));
        }
        return productResponseList; //응답객체 리스트 반환
    }

    @Override
    /*
 @Override
    public Product read(Long productId) {
        //Optional<Product> maybeProduct = productRepository.findById(productId);//이미지 사용 전 코드
        Optional<Product> maybeProduct = productRepository.findImagePathById(productId);//이미지 사용 후 코드

        if (maybeProduct.isEmpty()) {
            log.info("읽을 수가 없드아!");
            return null;
        }
        return maybeProduct.get();
    }
*/
    //이미지랑 글내용이랑 액션에서 두 메서드로 각각 가져오겠다는 의미. UI에서 백엔드의 2개의 맵핑메서드에 전송
    public ProductReadResponse read(Long productId) {
        Optional<Product> maybeProduct = productRepository.findById(productId);
        if (maybeProduct.isEmpty()) {
            log.info("읽을 수가 없드아!");
            return null;
        }
        Product product = maybeProduct.get();
        ProductReadResponse productReadResponse = new ProductReadResponse(
                product.getProductId(), product.getProductName(), product.getWriter(),
                product.getContent(), product.getPrice(), product.getRegDate()
        );
        //글내용만 보내기
        return productReadResponse;
    }

    @Override
    public void remove(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product modify(Long productId, ProductRequest productRequest) {
        Optional<Product> maybeProduct = productRepository.findById(productId);

        if (maybeProduct.isEmpty()) {
            System.out.println("Product 정보를 찾지 못했습니다: " + productId);
            return null;
        }

        Product product = maybeProduct.get();
        product.setProductName(productRequest.getProductName());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);

        return product;
    }
    @Override
//이미지랑 글내용이랑 액션에서 두 메서드로 각각 가져오겠다는 의미. UI에서 백엔드의 2개의 맵핑메서드에 전송
    public List<ImageResourceResponse> findProductImage(Long productId) {
        List<ImageResource> imageResourceList = imageResourceRepository.findImagePathByProductId(productId);
        List<ImageResourceResponse> imageResourceResponseList = new ArrayList<>();
        for (ImageResource imageResource: imageResourceList) {
            System.out.println("imageResource path: " + imageResource.getImageResourcePath());
            imageResourceResponseList.add(new ImageResourceResponse(imageResource.getImageResourcePath()));
            //패스가 적힌 응답객체를 만들어서 리스트에 넣기
        }
        return imageResourceResponseList;//각 응답객체마다 파일path가 적힌 리스트 반환
    }
}
