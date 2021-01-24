package com.guigehling.healthcare.helper;

import com.guigehling.healthcare.dto.InstitutionDTO;
import com.guigehling.healthcare.entity.Institution;
import com.guigehling.healthcare.entity.Wallet;

import java.math.BigDecimal;

public class BuilderHelper {

    public static Institution institutionBuilder(InstitutionDTO institutionDTO) {
        return Institution.builder()
                .name(institutionDTO.getName())
                .cnpj(institutionDTO.getCnpj())
                .build();
    }

    public static InstitutionDTO institutionDTOBuilder(Institution institution) {
        return InstitutionDTO.builder()
                .idInstitution(institution.getIdInstitution())
                .name(institution.getName())
                .cnpj(institution.getCnpj())
                .build();
    }

    public static Wallet walletBuilder(Long idInstitution, BigDecimal coin) {
        return Wallet.builder()
                .idInstitution(idInstitution)
                .coin(coin)
                .build();
    }
}
