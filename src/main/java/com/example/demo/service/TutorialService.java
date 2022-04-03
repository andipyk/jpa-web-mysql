package com.example.demo.service;

import com.example.demo.dto.TutorialDTO;
import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorialService {
    private final TutorialRepository tutorialRepository;

    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

//    @Autowired
//    private

    public List<TutorialDTO> getTutorials (){
        // split
        return tutorialRepository.findAll().stream().map(tutorial -> TutorialDTO.builder()
                .id(tutorial.getId())
                .description(tutorial.getDescription())
                .published(tutorial.isPublished())
                .title(tutorial.getTitle())
                .build()).collect(Collectors.toList());
//
//        try {
//            List<Tutorial> tutorials = new ArrayList<Tutorial>();
//            if (title == null)
//                tutorials.addAll(tutorialRepository.findAll());
//            else
//                tutorials.addAll(tutorialRepository.findByTitleContaining(title));
//            if (tutorials.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
    public TutorialDTO getTutorialById(long id) throws NotFoundException {
        return tutorialRepository.findById(id).map(tutorial -> TutorialDTO.builder()
                .id(tutorial.getId())
                .description(tutorial.getDescription())
                .published(tutorial.isPublished())
                .title(tutorial.getTitle())
                .build())
                .orElseThrow(()->new NotFoundException("Tutorial Tidak Ditemukan"));
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




}
