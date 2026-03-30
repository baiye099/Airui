package org.example.springboot01.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/file")
public class FileDownloadController {
    @GetMapping("/download")
    public void download(String path, HttpServletResponse response) {
        //根据客户传递的文件创建文件对象
        File file = new File(path);
        if (!file.exists()) {
            //如果文件不存在，直接响应404
            response.setStatus(404);
            return;
        }
        //设置响应头，文件以文件流的形式传输文件
        response.setContentType("application/octet-stream");
        try {
            response.setContentType("application/octet-stream");
            String filename = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    



}
