package com.supos.progress.controller;

import com.alibaba.fastjson.JSONObject;
import com.supos.progress.util.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping(value = "/excel",method = RequestMethod.POST)
    public JSONObject uploadExcel(@RequestParam("file")MultipartFile file){
        JSONObject jsonObject = new JSONObject();
        String fileName = file.getOriginalFilename();
        String filepath = "/excel/";
        try {
            FileUtil.uploadFile(file.getBytes(),filepath,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("errcode",-101);
            jsonObject.put("data","upload file");
            return jsonObject;
        }
        jsonObject.put("errcode",0);
        jsonObject.put("data","upload success");

        return jsonObject;
    }

    @RequestMapping(value = "/images",method = RequestMethod.POST)
    public JSONObject uploadImages(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("files");
        FileUtil.uploadMultiFile(files);
        return new JSONObject(){{put("errcode",0);put("data","upload success");}};
    }
}
