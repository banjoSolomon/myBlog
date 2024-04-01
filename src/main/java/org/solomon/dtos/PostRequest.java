package org.solomon.dtos;

import lombok.Data;

@Data
public class PostRequest {
    private String Username;
    private String title;
    private String content;
}
