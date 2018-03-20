package com.supos.progress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UploadViewController {
    @RequestMapping("/upload/view")
    public String goUpload(){
        return "uploadExcel";
    }
}
