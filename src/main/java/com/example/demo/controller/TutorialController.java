package com.example.demo.controller;

import com.example.demo.dto.TutorialDTO;
import com.example.demo.model.Tutorial;
import com.example.demo.service.TutorialService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {
    private final TutorialService tutorialService;

    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TutorialDTO>> getAllTutorials() {
        return ResponseEntity.ok(tutorialService.getTutorials());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorialDTO> getTutorialById(@PathVariable("id") long id) throws NotFoundException {
        return ResponseEntity.ok(tutorialService.getTutorialById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody TutorialDTO tutorial) {
        return ResponseEntity.ok(tutorialService.createTutorial(tutorial));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        return ResponseEntity.ok(tutorialService.updateTutorial(id, tutorial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        return ResponseEntity.ok(tutorialService.deleteTutorial(id));
    }

    @DeleteMapping("/")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        return ResponseEntity.ok(tutorialService.deleteAllTutorials());

    }

    @GetMapping("/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        return ResponseEntity.ok(tutorialService.findByPublished());

    }
}