package com.dreamgenics.spring.datajpa.model;


import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String team;
    private Integer moves;
    private Long durationSecs;

}
