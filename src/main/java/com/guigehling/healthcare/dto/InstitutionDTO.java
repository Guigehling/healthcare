package com.guigehling.healthcare.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;

@With
@Value
@JsonDeserialize(builder = InstitutionDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class InstitutionDTO {

    Long idInstitution;

    @NotNull
    @NotBlank
    String name;

    @CNPJ
    String cnpj;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
