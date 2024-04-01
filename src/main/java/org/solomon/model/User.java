package org.solomon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("User")
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private LocalDateTime dateRegistered = LocalDateTime.now();
    @Id
    private String id;
    @DBRef
    private List<Post> posts = new ArrayList<>();
}
