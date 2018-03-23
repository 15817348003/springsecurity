package com.sanmina.web.controller;

import com.sanmina.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    private String folder = "/home/cat/IdeaProjects/sanminasecurity/sanmina-security-demo/src/main/java/com/sanmina/web/controller";

    @PostMapping
    /**
     * 传过来的文件参数名为file，所以这里接收的参数名也要为file
     */
    public FileInfo upload(MultipartFile file) throws Exception {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());


         File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) throws Exception {
        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream outputStream = response.getOutputStream()) {

            response.setContentType("application/x-download");
            //用来定义我们下载时的文件名
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
