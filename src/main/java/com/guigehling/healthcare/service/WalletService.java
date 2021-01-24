package com.guigehling.healthcare.service;

import com.guigehling.healthcare.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.guigehling.healthcare.helper.BuilderHelper.walletBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService {

    private static final BigDecimal STARTING_COINS = BigDecimal.valueOf(20L);

    private final WalletRepository walletRepository;

    public Long create(Long idInstitution) {
        var wallet = walletRepository.save(walletBuilder(idInstitution, STARTING_COINS));
        return wallet.getIdWallet();
    }

}
