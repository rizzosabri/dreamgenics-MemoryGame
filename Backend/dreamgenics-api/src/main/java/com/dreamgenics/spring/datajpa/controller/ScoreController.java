package com.dreamgenics.spring.datajpa.controller;

import com.dreamgenics.spring.datajpa.model.Score;
import com.dreamgenics.spring.datajpa.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/score")
@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping
    public List<Score> getTop10Scores() {
        return scoreService.getTop10Scores();
    }

    @PostMapping
    public Score createScore(@RequestBody Score score) {
        return scoreService.save(score);
    }

    // se realiza para completar el CRUD

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        scoreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Score> upDateScore(@PathVariable Long id,@RequestBody Score score) {
        return scoreService.findById(id)
                .map(existingScore -> {
                    score.setId(existingScore.getId());
                    return ResponseEntity.ok(scoreService.save(score));
                })
                .orElse(ResponseEntity.notFound().build());
    }


}