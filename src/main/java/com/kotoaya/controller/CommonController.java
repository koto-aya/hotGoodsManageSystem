package com.kotoaya.controller;

import com.kotoaya.common.CustomException;
import com.kotoaya.common.Result;
import com.kotoaya.common.ResultEnum;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/system")
@CrossOrigin
public class CommonController {
    /**
     * @author wzw
     * 文件上传模块
     */
    @PostMapping("/upload")
    public Result<String> uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()){
            throw new CustomException(ResultEnum.FILE_NOT_EXIST);
        }
        //获取文件名后缀
        String originalFilename = file.getOriginalFilename();
        String prefix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        //生成uuid作为文件名
        String fileName = generateUUID()+prefix;
        String savePath = "E:\\project\\热门商品管理系统\\hotGoodsManageSystem\\target\\hotGoodsManageSystem-1.0-SNAPSHOT\\WEB-INF\\classes\\img\\"+fileName;
        file.transferTo(new File(savePath));
        return Result.ok("http://localhost:8080/hotGoodsManageSystem/img/"+fileName);
    }
    /**
     * @author wzw
     * 生成uuid
     */
    private String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
