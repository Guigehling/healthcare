package com.guigehling.healthcare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam", schema = "healthcare")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExam;
    private Long idInstitution;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String physicianName;
    private String physicianCrm;
    private String procedureName;

}
