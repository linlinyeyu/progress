package com.supos.progress.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    public static void uploadFile(byte[] file,String filePath,String fileName){
        File targetFile = new File(filePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath+fileName);
            outputStream.write(file);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uploadMultiFile(List<MultipartFile> files){
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i=0;i<files.size();++i){
            file = files.get(i);
            if (!file.isEmpty()){
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File("/images/"+file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<String> getNames(File file){
        File[] files = file.listFiles();
        List<String> names = new ArrayList<>();
        Arrays.stream(files).forEach(r->{
            names.add("http://localhost/images/"+r.getName());
        });
        return names;
    }
}
