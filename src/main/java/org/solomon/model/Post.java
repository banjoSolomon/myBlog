package org.solomon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document("post")
public class Post {

    private String title;
    private String content;
    @Id
    private String id;
    private LocalDateTime dateCreated = LocalDateTime.now();
    @DBRef
    private List<Views> views;
    @DBRef
    private List<Comment>comments;
}
