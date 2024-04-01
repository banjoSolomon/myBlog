package org.solomon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Comment {
    @Id
    private String id;
    @DBRef
    private String commenter;
    private String content;
    private LocalDateTime timeOfComment;
    private List<Views> views;


}
