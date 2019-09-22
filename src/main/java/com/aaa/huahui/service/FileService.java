package com.aaa.huahui.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    private String file_base_path;
    private String filepath_pattern;

    @Autowired
    public FileService(@Value("${file_base_path}") String file_upload_base_path, @Value("${filepath_pattern}") String filepath_pattern) {
        if (file_upload_base_path.endsWith("/")) {
            this.file_base_path = file_upload_base_path;
        } else {
            this.file_base_path = file_upload_base_path + "/";
        }


        if (filepath_pattern.endsWith("/")) {
            this.filepath_pattern = filepath_pattern;
        } else {
            this.filepath_pattern = filepath_pattern + "/";
        }

    }


    public String uploadFile(MultipartFile file, String subpath) {

        if (subpath.startsWith("/")) {
            subpath = subpath.substring(1, subpath.length());
        }
        if (!subpath.endsWith("/")) {
            subpath = subpath + "/";
        }

        String tempName = UUID.randomUUID().toString();
        String originalFilename = file.getOriginalFilename();
        String extname = "";

        if (originalFilename.contains(".")) {
            String[] namepart = originalFilename.split("\\.");
            extname = namepart[namepart.length - 1];
        }
        if (!extname.equals("")) {
            tempName = tempName + "." + extname;
        }
        String pathname = file_base_path + subpath + tempName;

        File tempFile = new File(pathname);
        File path = tempFile.getParentFile();
        boolean a = path.mkdirs();

        try {
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.filepath_pattern + subpath + tempName;

    }


    //上传图片
    public String uploadImage(MultipartFile file) {
        return uploadFile(file, "/img");
    }

    /**
     * 删除图片
     * **/
    public boolean deleteImage(String filename){
        File f=new File(file_base_path+"/img/"+filename);
        return f.delete();
    }


    //上传视频
    public String uploadVideo(MultipartFile file) {
        return uploadFile(file, "/video");
    }

    //上传音频
    public String uploadAudio(MultipartFile file) {
        return uploadFile(file, "/audio");
    }

    //上传文档
    public String uploadDoc(MultipartFile file) {
        return uploadFile(file, "/doc");
    }

    //上传其他
    public String uploadOther(MultipartFile file) {
        return uploadFile(file, "/other");
    }

}
