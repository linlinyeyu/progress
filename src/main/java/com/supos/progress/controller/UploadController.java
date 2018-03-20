package com.supos.progress.controller;

import com.supos.progress.util.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping(value = "/excel",method = RequestMethod.POST)
    public String uploadExcel(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        String filepath = "./excel/";
        try {
            FileUtil.uploadFile(file.getBytes(),filepath,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return "upload file";
        }
        return "upload success";
    }
}
