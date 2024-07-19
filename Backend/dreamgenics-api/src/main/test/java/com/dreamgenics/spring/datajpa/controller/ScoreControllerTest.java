package com.dreamgenics.spring.datajpa.controller;

import com.dreamgenics.spring.datajpa.model.Score;
import com.dreamgenics.spring.datajpa.services.ScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScoreControllerTest {

    @InjectMocks
    private ScoreController scoreController;

    @Mock
    private ScoreService scoreService;
    private List<Score> top10Scores;
    private Score score1;
    private Score score2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        score1 = new Score();
        score1 = new Score();
        score1.setId(1L);
        score1.setName("Player1");
        score1.setTeam("TeamA");
        score1.setMoves(5);
        score1.setDurationSecs(100L);

        score2 = new Score();
        score2.setId(2L);
        score2.setName("Player2");
        score2.setTeam("TeamB");
        score2.setMoves(10);
        score2.setDurationSecs(200L);

        top10Scores = Arrays.asList(score1, score2);

    }

    @Test
    public void createScore_ShouldReturnSavedScore() {
        // Arrange
        when(scoreService.save(any(Score.class))).thenReturn(score1);

        // Act
        Score createdScore = scoreController.createScore(score1);

        // Assert
        assertNotNull(createdScore);
        assertEquals(5, createdScore.getMoves());
    }


    @Test
    public void getTop10Scores_ShouldReturnTop10Scores() {
        // Arrange
        when(scoreService.getTop10Scores()).thenReturn(top10Scores);

        // Act
        List<Score> result = scoreController.getTop10Scores();

        // Assert
        assertEquals(top10Scores, result);
        assertEquals(2, result.size()); // Verificar que la lista contiene dos elementos
        assertEquals("Player1", result.get(0).getName()); // Verificar el primer elemento
        assertEquals("Player2", result.get(1).getName()); // Verificar el segundo elemento
    }

}
