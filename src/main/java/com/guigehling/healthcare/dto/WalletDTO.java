package com.guigehling.healthcare.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@With
@Value
@JsonDeserialize(builder = WalletDTO.JacksonBuilder.class)
@Builder(builderClassName = "JacksonBuilder")
public class WalletDTO {

    Long idWallet;
    @Positive
    Long idInstitution;
    BigDecimal coin;

    @JsonPOJOBuilder(withPrefix = "")
    public static class JacksonBuilder {
    }
}
