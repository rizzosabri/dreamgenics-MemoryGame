package com.dreamgenics.spring.datajpa.services;

import com.dreamgenics.spring.datajpa.model.Score;
import com.dreamgenics.spring.datajpa.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScoreService {


    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getTop10Scores() {
        return scoreRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Score::getMoves)) // Ordenar por cantidad de movimientos
                .limit(10) // Limitar a los primeros 10
                .collect(Collectors.toList());
    }

    public Score save(Score score) {
        return scoreRepository.save(score);
    }

// se realiza para completar el CRUD
    public void deleteById(Long id) {
        scoreRepository.deleteById(id);
    }

    public Optional<Score> findById(Long id) {
        return scoreRepository.findById(id);
    }
}
