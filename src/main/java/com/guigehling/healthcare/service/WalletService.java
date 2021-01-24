package com.guigehling.healthcare.service;

import com.guigehling.healthcare.dto.WalletDTO;
import com.guigehling.healthcare.exception.BusinessException;
import com.guigehling.healthcare.helper.MessageHelper;
import com.guigehling.healthcare.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.guigehling.healthcare.enumeration.ErrorCodeEnum.ERROR_WALLET_GET_BY_INSTITUTION;
import static com.guigehling.healthcare.helper.BuilderHelper.walletBuilder;
import static com.guigehling.healthcare.helper.BuilderHelper.walletDTOBuilder;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletService {

    private static final BigDecimal STARTING_COINS = BigDecimal.valueOf(20L);

    private final WalletRepository walletRepository;

    private final MessageHelper messageHelper;

    public Long create(Long idInstitution) {
        var wallet = walletRepository.save(walletBuilder(idInstitution, STARTING_COINS));
        return wallet.getIdWallet();
    }

    public WalletDTO update(WalletDTO walletDTO) {
        var wallet = walletRepository.save(walletBuilder(walletDTO));
        return walletDTOBuilder(wallet);
    }

    public WalletDTO getWalletByInstitution(Long idInstitution) {
        var wallet = walletRepository.findByIdInstitution(idInstitution);
        if (wallet.isPresent())
            return walletDTOBuilder(wallet.get());

        throw new BusinessException(NOT_FOUND,
                messageHelper.get(ERROR_WALLET_GET_BY_INSTITUTION, String.valueOf(idInstitution)));
    }

}
