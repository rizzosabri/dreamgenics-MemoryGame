package com.dreamgenics.spring.datajpa.repository;

import com.dreamgenics.spring.datajpa.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
