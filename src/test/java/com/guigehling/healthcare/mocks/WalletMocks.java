package com.guigehling.healthcare.mocks;


import com.guigehling.healthcare.dto.WalletDTO;

import java.math.BigDecimal;

public class WalletMocks {

    public static WalletDTO getWalletWitBalance() {
        return WalletDTO.builder()
                .idInstitution(1L)
                .idWallet(1L)
                .coin(BigDecimal.TEN)
                .build();
    }

    public static WalletDTO getWalletWithoutBalance() {
        return WalletDTO.builder()
                .idInstitution(1L)
                .idWallet(1L)
                .coin(BigDecimal.ZERO)
                .build();
    }

}
