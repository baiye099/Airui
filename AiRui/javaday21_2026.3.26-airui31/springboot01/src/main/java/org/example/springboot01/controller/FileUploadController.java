package org.example.springboot01.controller;

import org.example.springboot01.config.FileUploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    // 在构造函数中初始化各种路径
    public FileUploadController(FileUploadConfig fileUploadConfig) {
        this.fileUploadConfig = fileUploadConfig;
        // 定义基础路径
        Path basePath = Paths.get(fileUploadConfig.getBasePath());
        // 定义头像路径 【基础路径+分隔符+头像目录名】
        Path avatarDirPath = Paths.get(fileUploadConfig.getBasePath() + File.separator + fileUploadConfig.getAvatar().getDir());
        // 定义身份证路径 【基础路径+分隔符+身份证目录名】
        Path idCardDirPath = Paths.get(fileUploadConfig.getBasePath() + File.separator + fileUploadConfig.getIdCard().getDir());
        // 定义文档路径 【基础路径+分隔符+文档目录名】
        Path documentDirPath = Paths.get(fileUploadConfig.getBasePath() + File.separator + fileUploadConfig.getDocument().getDir());
        // 顶excel上传路径 【基础路径+分隔符+excel目录名】
        Path excelDirPath = Paths.get(fileUploadConfig.getBasePath() + File.separator + fileUploadConfig.getExcel().getDir());
        try {
            if (!Files.exists(basePath)) Files.createDirectories(basePath);
            if (!Files.exists(avatarDirPath)) Files.createDirectories(avatarDirPath);
            if (!Files.exists(idCardDirPath)) Files.createDirectories(idCardDirPath);
            if (!Files.exists(documentDirPath)) Files.createDirectories(documentDirPath);
            if (!Files.exists(excelDirPath)) Files.createDirectories(excelDirPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/upload/avatar")
    public Object uploadAvatar(MultipartFile avatarFile) {
        // 1. 定义map, 用于返回结果
        Map<String, Object> resultMap = new HashMap<>();
        // 2. 对头像文件进行判空
        if (avatarFile == null) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您没有选择要上传的头像!");
            return resultMap;
        }
        // 3. 取出头像的配置信息
        // 外部类.内部类 作为avatar的类型
        FileUploadConfig.Avatar avatar = fileUploadConfig.getAvatar();
        // 4. 校验文件类型是否合法
        // 获取源文件的内容类型
        String contentType = avatarFile.getContentType();
        // avatar.getAllowTypes()返回的是一个List集合,里面包含配置的多个文件类型值
        // contains方法是List的方法, 用于判断某个值是否在List的所有取值范围之内
        if (!avatar.getAllowTypes().contains(contentType)) {
            resultMap.put("result", "error");
            resultMap.put("reason", "文件格式不支持!");
            return resultMap;
        }
        // 5. 校验文件大小是否合法
        // 拿到源文件的大小 (单位是字节)
        long size = avatarFile.getSize();
        // 如果我们配置的头像最大size < 源文件的size
        if (avatar.getMaxSize() < size) {
            resultMap.put("result", "error");
            resultMap.put("reason", "文件Size太大, 不能超过" + avatar.getMaxSize() + "个字节");
        }
        // 6. 如果上面的校验都通过了, 生成新的文件名, 准备文件落地
        String originalFilename = avatarFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString() + suffix;
        // 拼接文件保存路径
        // 【D:/upload/files】/【avatar】/【新文件名】
        String savePath = fileUploadConfig.getBasePath() + File.separator
                + avatar.getDir() + File.separator + newFilename;
        try {
            avatarFile.transferTo(new File(savePath));
            resultMap.put("result", "success");
            resultMap.put("savePath", savePath);
            resultMap.put("newFilename", newFilename);
        } catch (IOException e) {
            resultMap.put("result", "error");
            resultMap.put("reason", e.getMessage());
            throw new RuntimeException(e);
        }
        return resultMap;
    }


    @PostMapping("/upload/id-card")
    public Object uploadIdCard(MultipartFile idcardFile) {
        //1.
        Map<String, Object> resultMap = new HashMap<>();
        //2.
        if (idcardFile == null) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您没有选择要上传的身份证照片!");
            return resultMap;
        }
        //3.
        FileUploadConfig.IdCard idCard = fileUploadConfig.getIdCard();
        String contentType = idcardFile.getContentType();
        if (!idCard.getAllowTypes().contains(contentType)) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您上传的文件格式不正确");
            return resultMap;
        }
        long size = idcardFile.getSize();
        if (idCard.getMaxSize() < size) {
            resultMap.put("result", "error");
            resultMap.put("reason", "文件Size太大, 不能超过" + idCard.getMaxSize() + "个字节");
            return resultMap;
        }
        String originalFilename = idcardFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString() + suffix;

        String savePath = fileUploadConfig.getBasePath() + File.separator + idCard.getDir() + File.separator + newFilename;
        try {
            idcardFile.transferTo(new File(savePath));
            resultMap.put("result", "success");
            resultMap.put("savePath", savePath);
            resultMap.put("newFilename", newFilename);
        } catch (IOException e) {
            resultMap.put("result", "error");
            resultMap.put("reason", e.getMessage());
            throw new RuntimeException(e);
        }


        return resultMap;
    }

    @PostMapping("/upload/document")
    public Object uploadDocument(MultipartFile document) {
        return null;
    }

    @PostMapping("/upload/excel")
    public Object uploadExcel(MultipartFile excel) {
        return null;
    }




   /* @GetMapping("/config")
    public Object config() {
        System.out.println(fileUploadConfig.getAvatar());
        System.out.println();
        System.out.println(fileUploadConfig.getDocument());
        System.out.println(fileUploadConfig.getExcel());
        System.out.println(fileUploadConfig.getIdCard());
        return "success";
    }*/

}
