package org.example.RestaurantManageSystem.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.*;
import java.nio.file.Files;

@RestController
@RequestMapping("/file")
public class FileDownloadController {

    @GetMapping("/download")
    public void download(@RequestParam("path") String path, HttpServletResponse response) {
        File file = new File(path);
        if (!file.exists()) {
            response.setStatus(404);
            return;
        }

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