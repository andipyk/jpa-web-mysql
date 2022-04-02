package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class TutorialDTO {
    private long id;
    private String title;
    private String description;
    private boolean published;
}
