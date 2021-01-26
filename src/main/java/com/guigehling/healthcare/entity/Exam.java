package com.guigehling.healthcare.entity;

import com.guigehling.healthcare.enumeration.GenderTypeEnum;
import lombok.*;

import javax.persistence.*;

@With
@Getter
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
    private Integer patientAge;
    @Enumerated(EnumType.STRING)
    private GenderTypeEnum patientGender;
    private String physicianName;
    private String physicianCrm;
    private String procedureName;
    private boolean consulted;

}
