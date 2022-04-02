package com.example.demo.service;

import com.example.demo.dto.TutorialDTO;
import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TutorialServiceTest {

    @InjectMocks
    private TutorialService tutorialService;

    @Mock
    private TutorialRepository tutorialRepository;

    @Test
    void getTutorials() {
        List<Tutorial> tutorials = Arrays.asList(new Tutorial());
        when(tutorialRepository.findAll()).thenReturn(tutorials);

        List<TutorialDTO> tutorialDTOList = tutorialService.getTutorials();

        Assertions.assertEquals(tutorialDTOList.size(), tutorials.size());
    }

    @Test
    void getTutorialById() throws NotFoundException {
        Tutorial tutorial = new Tutorial();
        tutorial.setId(1L);
        when(tutorialRepository.findById(anyLong())).thenReturn(Optional.of(tutorial));

        TutorialDTO tutorialDTO = tutorialService.getTutorialById(1L);
        Assertions.assertEquals(tutorialDTO.getId(), tutorial.getId());
    }

    @Test
    void getTutorialThenThrowNotFoundException(){
        when(tutorialRepository.findById(anyLong())).thenReturn(Optional.empty());
        try{
            tutorialService.getTutorialById(1L);
        }catch (Exception e){
            assertEquals(e.getClass(),NotFoundException.class);
        }
    }
}