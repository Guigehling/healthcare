package com.guigehling.healthcare.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.guigehling.healthcare.enumeration.GenderTypeEnum;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@With
@Value
@JsonDeserialize(builder = ExamDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class ExamDTO {

    Long idExam;
    @Positive
    Long idInstitution;
    @NotNull
    @NotBlank
    String patientName;
    Integer patientAge;
    GenderTypeEnum patientGender;
    String physicianName;
    String physicianCrm;
    String procedureName;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
