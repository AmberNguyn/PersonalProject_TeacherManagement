package com.example.TeacherManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TeacherObservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDate observationDate;

    private String feedback;

    private double score;

    @ManyToOne
    @JoinColumn(name = "assignment_detail_id")
    private AssignmentDetail assignmentDetail;

    @ManyToOne
    @JoinColumn(name = "training_quality_manager_id")
    private TrainingQualityManager trainingQualityManager;
}
