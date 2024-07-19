package com.dreamgenics.spring.datajpa.steps;


import com.dreamgenics.spring.datajpa.controller.ScoreController;
import com.dreamgenics.spring.datajpa.model.Score;
import com.dreamgenics.spring.datajpa.services.ScoreService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ScoreControllerSteps {

    @InjectMocks
    private ScoreController scoreController;

    @Mock
    private ScoreService scoreService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        score = new Score();
        score.setMoves(5);
    }

    private Score score;
    private Score createdScore;

    public ScoreControllerSteps() {
        MockitoAnnotations.initMocks(this);
    }

    @Given("a new score with {int} moves")
    public void aNewScoreWithMoves(int moves) {
        score = new Score();
        score.setMoves(moves);
    }

    @When("the score is saved")
    public void theScoreIsSaved() {
        when(scoreService.save(any(Score.class))).thenReturn(score);
        createdScore = scoreController.createScore(score);
    }

    @Then("the saved score should have {int} moves")
    public void theSavedScoreShouldHaveMoves(int moves) {
        assertNotNull(createdScore);
        assertEquals(moves, createdScore.getMoves());
    }
}
