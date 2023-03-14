package com.example.demo.domain.fileTest.controller;

import com.example.demo.domain.fileTest.controller.request.RequestFileInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class FileController {

    @PostMapping(value = "/uploadImgsWithText",
                consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })//순서상관없음
                                            //첫번째 매개변수로 파일리스트,  두번째 매개변수로 글자처리 .
    public String requestFileUploadWithText(@RequestPart(value = "imageFileList") List<MultipartFile> fileList,
                                                @RequestPart(value = "info") RequestFileInfo info )//매개변수2개
            //UI에서 formData안에 이름을 imageFileList, info로 지어서 보냈기때문에 value를 저렇게 받아올수 있는것.
    { //함수시작
        log.info("글자출력"+info);//리퀘스트객체에 ToString을 만들어야 함
        try {
            for (MultipartFile multipartFile: fileList) {
                log.info("requestFileUploadWithText() - filename: " + multipartFile.getOriginalFilename());
                
                //사용자 계정 이름마다 별도로 사진을 배치하게 구성
                //등록한 시간을 파일명 어딘가에 붙여서 파일을 저장.
                FileOutputStream writer = new FileOutputStream(
                        "../../Vue.js/home_frontend/src/assets/uploadImgs/" +
                                multipartFile.getOriginalFilename()
                );
                writer.write(multipartFile.getBytes());
                writer.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Upload Success!!!";
    }
}