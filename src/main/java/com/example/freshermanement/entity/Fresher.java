package com.example.freshermanement.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Fresher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String fresherCode;
    private String name;
    private String programmingLanguage;
    private String email;

    @ElementCollection
    private List<Double> projectScores;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne
    private Center center;

    // New field for the final average score
    private Double finalAverageScore;

    // Method to calculate and update the final average score
    public void calculateAndUpdateFinalAverageScore() {
        if (projectScores == null || projectScores.isEmpty()) {
            throw new IllegalStateException("Project scores are not available.");
        }
        double sum = 0;
        for (Double score : projectScores) {
            sum += score;
        }
        this.finalAverageScore = sum / projectScores.size();
    }
}