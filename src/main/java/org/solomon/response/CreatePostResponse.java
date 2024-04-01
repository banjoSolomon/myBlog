package org.solomon.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreatePostResponse {
    private String title;
    private String content;
    private String id;
    private String dateCreated;
}
