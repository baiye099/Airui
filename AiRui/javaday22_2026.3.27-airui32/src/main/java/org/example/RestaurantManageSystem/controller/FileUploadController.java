package org.example.RestaurantManageSystem.controller;

import org.example.RestaurantManageSystem.config.FileUploadConfig;
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
        Path basePath = Paths.get(fileUploadConfig.getBasePath());
        Path StoreImageDirPath = Paths.get(fileUploadConfig.getBasePath() + File.separator + fileUploadConfig.getStoreImage().getDir());
        Path DishImageDirPath = Paths.get(fileUploadConfig.getBasePath() + File.separator + fileUploadConfig.getDishImage().getDir());
        Path BusinessLicenseDirPath = Paths.get(fileUploadConfig.getBasePath() + File.separator + fileUploadConfig.getBusinessLicense().getDir());
        Path RentalContrac = Paths.get(fileUploadConfig.getBasePath() + File.separator + fileUploadConfig.getRentalContract().getDir());
        try {
            if (!Files.exists(basePath)) Files.createDirectories(basePath);
            if (!Files.exists(StoreImageDirPath)) Files.createDirectories(StoreImageDirPath);
            if (!Files.exists(DishImageDirPath)) Files.createDirectories(DishImageDirPath);
            if (!Files.exists(BusinessLicenseDirPath)) Files.createDirectories(BusinessLicenseDirPath);
            if (!Files.exists(RentalContrac)) Files.createDirectories(RentalContrac);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/upload/store-image")
    public Object upStoreImage(MultipartFile storepicturesFile) {
        //1.
        Map<String, Object> resultMap = new HashMap<>();
        //2.
        if (storepicturesFile == null) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您没有选择要上传的身份证照片!");
            return resultMap;
        }
        //3.
        FileUploadConfig.StoreImage storePictures = fileUploadConfig.getStoreImage();
        String contentType = storepicturesFile.getContentType();
        if (!storePictures.getAllowTypes().contains(contentType)) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您上传的文件格式不正确");
            return resultMap;
        }
        long size = storepicturesFile.getSize();
        if (storePictures.getMaxSize() < size) {
            resultMap.put("result", "error");
            resultMap.put("reason", "文件Size太大, 不能超过" + storePictures.getMaxSize() + "个字节");
            return resultMap;
        }
        String originalFilename = storepicturesFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString() + suffix;

        String savePath = fileUploadConfig.getBasePath() + File.separator + storePictures.getDir() + File.separator + newFilename;
        try {
            storepicturesFile.transferTo(new File(savePath));
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


    /*    @PostMapping("/upload/dish-image")
        public Object uploadDishImage(@RequestParam("dishImageFile") MultipartFile file) {
            return uploadFile(file, fileUploadConfig.getDishImage().getDir(),
                    fileUploadConfig.getDishImage().getMaxSize(),
                    fileUploadConfig.getDishImage().getAllowTypes());
        }*/
    @PostMapping("/upload/dish-image")
    public Object uploadDishImage(MultipartFile dishimageFile) {
        //1.
        Map<String, Object> resultMap = new HashMap<>();
        //2.
        if (dishimageFile == null) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您没有选择要上传的身份证照片!");
            return resultMap;
        }
        //3.
        FileUploadConfig.DishImage dishImage = fileUploadConfig.getDishImage();
        String contentType = dishimageFile.getContentType();
        if (!dishImage.getAllowTypes().contains(contentType)) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您上传的文件格式不正确");
            return resultMap;
        }
        long size = dishimageFile.getSize();
        if (dishImage.getMaxSize() < size) {
            resultMap.put("result", "error");
            resultMap.put("reason", "文件Size太大, 不能超过" + dishImage.getMaxSize() + "个字节");
            return resultMap;
        }
        String originalFilename = dishimageFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString() + suffix;

        String savePath = fileUploadConfig.getBasePath() + File.separator + dishImage.getDir() + File.separator + newFilename;
        try {
            dishimageFile.transferTo(new File(savePath));
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


/*    @PostMapping("/upload/business-license")
    public Object uploadBusinessLicense(@RequestParam("businessLicenseFile") MultipartFile file) {
        return uploadFile(file, fileUploadConfig.getBusinessLicense().getDir(),
                fileUploadConfig.getBusinessLicense().getMaxSize(),
                fileUploadConfig.getBusinessLicense().getAllowTypes());
    }*/

    @PostMapping("/upload/business-license")
    public Object uploadBusinessLicense(MultipartFile businesslicenseFile) {
        //1.
        Map<String, Object> resultMap = new HashMap<>();
        //2.
        if (businesslicenseFile == null) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您没有选择要上传的身份证照片!");
            return resultMap;
        }
        //3.
        FileUploadConfig.BusinessLicense businessLicense = fileUploadConfig.getBusinessLicense();
        String contentType = businesslicenseFile.getContentType();
        if (!businessLicense.getAllowTypes().contains(contentType)) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您上传的文件格式不正确");
            return resultMap;
        }
        long size = businesslicenseFile.getSize();
        if (businessLicense.getMaxSize() < size) {
            resultMap.put("result", "error");
            resultMap.put("reason", "文件Size太大, 不能超过" + businessLicense.getMaxSize() + "个字节");
            return resultMap;
        }
        String originalFilename = businesslicenseFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString() + suffix;

        String savePath = fileUploadConfig.getBasePath() + File.separator + businessLicense.getDir() + File.separator + newFilename;
        try {
            businesslicenseFile.transferTo(new File(savePath));
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

/*    @PostMapping("/upload/rental-contract")
    public Object uploadRentalContract(@RequestParam("rentalContractFile") MultipartFile file) {
        return uploadFile(file, fileUploadConfig.getRentalContract().getDir(),
                fileUploadConfig.getRentalContract().getMaxSize(),
                fileUploadConfig.getRentalContract().getAllowTypes());
    }*/

    @PostMapping("/upload/rental-contract")
    public Object uploadRenalContract(MultipartFile renalcontractFile) {
        //1.
        Map<String, Object> resultMap = new HashMap<>();
        //2.
        if (renalcontractFile == null) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您没有选择要上传的身份证照片!");
            return resultMap;
        }
        //3.
        FileUploadConfig.RentalContract rentalContract = fileUploadConfig.getRentalContract();
        String contentType = renalcontractFile.getContentType();
        if (!rentalContract.getAllowTypes().contains(contentType)) {
            resultMap.put("result", "error");
            resultMap.put("reason", "您上传的文件格式不正确");
            return resultMap;
        }
        long size = renalcontractFile.getSize();
        if (rentalContract.getMaxSize() < size) {
            resultMap.put("result", "error");
            resultMap.put("reason", "文件Size太大, 不能超过" + rentalContract.getMaxSize() + "个字节");
            return resultMap;
        }
        String originalFilename = renalcontractFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString() + suffix;

        String savePath = fileUploadConfig.getBasePath() + File.separator + rentalContract.getDir() + File.separator + newFilename;
        try {
            renalcontractFile.transferTo(new File(savePath));
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
  /*






    private Object uploadFile(MultipartFile file, String dir, long maxSize, java.util.List<String> allowTypes) {
        Map<String, Object> result = new HashMap<>();

        if (file.isEmpty()) {
            result.put("result", "error");
            result.put("reason", "文件为空");
            return result;
        }

        if (file.getSize() > maxSize) {
            result.put("result", "error");
            result.put("reason", "文件大小超过限制");
            return result;
        }

        String contentType = file.getContentType();
        if (!allowTypes.contains(contentType)) {
            result.put("result", "error");
            result.put("reason", "文件类型不允许");
            return result;
        }

        String basePath = fileUploadConfig.getBasePath();
        String uploadDir = basePath + File.separator + dir;
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + suffix;
        String savePath = uploadDir + File.separator + newFilename;

        try {
            file.transferTo(new File(savePath));
            result.put("result", "success");
            result.put("savePath", savePath);
            result.put("newFilename", newFilename);
        } catch (IOException e) {
            result.put("result", "error");
            result.put("reason", "文件上传失败: " + e.getMessage());
        }

        return result;
    }*/
}