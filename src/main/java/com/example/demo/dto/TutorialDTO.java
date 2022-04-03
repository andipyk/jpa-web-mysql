package com.example.demo.dto;

import com.example.demo.model.Tutorial;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class TutorialDTO {
    private final long id;
    private final String title;
    private final String description;
    private final boolean published;

//    public TutorialDTO(Tutorial tutorial, String description, boolean published) {
//        this.id = tutorial.getId();
//        this.title = tutorial.getTitle();
//        this.description = description;
//        this.published = published;
//    }

    public TutorialDTO(long id, String title, String description, boolean published) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.published = published;
    }
}
