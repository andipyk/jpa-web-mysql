package com.example.demo.service;

import com.example.demo.dto.TutorialDTO;
import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialRepository;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorialService {
    private final TutorialRepository tutorialRepository;

    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    public List<TutorialDTO> getTutorials() {
        return tutorialRepository.findAll().stream().map(tutorial -> TutorialDTO.builder()
                .id(tutorial.getId())
                .description(tutorial.getDescription())
                .published(tutorial.isPublished())
                .title(tutorial.getTitle())
                .build()).collect(Collectors.toList());
    }

    public TutorialDTO getTutorialById(long id) throws NotFoundException {
        return tutorialRepository.findById(id).map(tutorial -> TutorialDTO.builder()
                        .id(tutorial.getId())
                        .description(tutorial.getDescription())
                        .published(tutorial.isPublished())
                        .title(tutorial.getTitle())
                        .build())
                .orElseThrow(() -> new NotFoundException("Tutorial Tidak Ditemukan"));
    }

    public Tutorial createTutorial(TutorialDTO tutorialDTO) {
        Tutorial newTutorial = new Tutorial(
                tutorialDTO.getId(),
                tutorialDTO.getTitle(),
                tutorialDTO.getDescription(),
                false
        );

        return tutorialRepository.save(newTutorial);
    }

    public Tutorial updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Tutorial tutorialData = tutorialRepository.findById(id)
                .orElse(null);

        assert tutorialData != null;
        tutorialData.setTitle(tutorial.getTitle());
        tutorialData.setDescription(tutorial.getDescription());
        tutorialData.setPublished(tutorial.isPublished());

        return tutorialRepository.save(tutorialData);
    }

    public HttpStatus deleteTutorial(long id) {
        tutorialRepository.deleteById(id);
        return HttpStatus.OK;

    }

    public HttpStatus deleteAllTutorials() {
        tutorialRepository.deleteAll();
        return HttpStatus.OK;
    }

    public List<Tutorial> findByPublished() {
        return tutorialRepository.findByPublished(true);
    }

}
