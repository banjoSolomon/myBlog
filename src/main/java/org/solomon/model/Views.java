package org.solomon.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Views {
    private LocalDateTime timeOfView;
    private User viewer;
    @Id
    private String id;
}
