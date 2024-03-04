package com.example.casestudy.service;


import com.example.casestudy.model.enums.FileType;
import com.example.casestudy.service.post.request.PostSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadService {

    String UPLOAD_DIR = "F:\\Codegym\\Module 4\\New folder\\Premium_social_network\\src\\main\\resources\\assets\\postimage\\";
    String SAVE_UPLOAD_DIR = "\\assets\\postimage\\";
    public List<PostSaveRequest.ContentSaveRequest.MediaSaveRequest> addFileToRequest(MultipartFile[] multipartFiles){
        List<PostSaveRequest.ContentSaveRequest.MediaSaveRequest> list = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            PostSaveRequest.ContentSaveRequest.MediaSaveRequest media = new PostSaveRequest.ContentSaveRequest.MediaSaveRequest();

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            media.setUrl(fileName);
        }
        return list;
    }
    public List<PostSaveRequest.ContentSaveRequest.MediaSaveRequest> transferFiles(MultipartFile[] multipartFiles) throws IOException {
        List<PostSaveRequest.ContentSaveRequest.MediaSaveRequest> media = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            PostSaveRequest.ContentSaveRequest.MediaSaveRequest mediaSave = new PostSaveRequest.ContentSaveRequest.MediaSaveRequest();
            String fileName = file.getOriginalFilename();
            String filePath = UPLOAD_DIR + fileName;
            String savePath = SAVE_UPLOAD_DIR + fileName;
            String contentType = file.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                mediaSave.setFileType(FileType.IMAGE);
            } else  {
                mediaSave.setFileType(FileType.VIDEO);

            }
            mediaSave.setUrl(savePath);
            media.add(mediaSave);
            file.transferTo(new File(filePath));
        }
        return media;
    }
}
