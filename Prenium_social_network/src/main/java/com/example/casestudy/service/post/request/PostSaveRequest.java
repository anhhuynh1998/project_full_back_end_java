package com.example.casestudy.service.post.request;

import com.example.casestudy.model.enums.FileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSaveRequest {
    private ContentSaveRequest content;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ContentSaveRequest{
        private String text;
        private List<MediaSaveRequest> media;
        //        public ContentSaveRequest(){
//            this.media = new ArrayList<>();
//            this.media.add(new MediaSaveRequest());
//        }
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class MediaSaveRequest{
            private String url;
            private FileType fileType;
        }
    }
}
