package com.piano.controller.admin;

import com.piano.constant.MessageConstant;
import com.piano.result.Result;
import com.piano.utils.S3Util;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {
    @Autowired
    private S3Util s3Util;

    @PostMapping("/upload")
    @ApiOperation("upload file")
    public Result<String> upload(MultipartFile file) {
        log.info("upload file: {}", file.getOriginalFilename());

        try {
            // get original filename
            String originalFilename = file.getOriginalFilename();
            // get file extension
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // create object name
            String objectName = UUID.randomUUID().toString() + extension;
            String filePath = s3Util.upload(file.getBytes(), objectName);
            return Result.success(filePath);

        } catch (IOException e) {
            log.error("upload file error", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
