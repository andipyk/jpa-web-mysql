package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TutorialRequestDTO {
    private String title;
    private String description;
    private boolean published;
}
