package com.guigehling.healthcare.service;

import com.guigehling.healthcare.dto.InstitutionDTO;
import com.guigehling.healthcare.exception.BusinessException;
import com.guigehling.healthcare.helper.MessageHelper;
import com.guigehling.healthcare.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import static com.guigehling.healthcare.enumeration.ErrorCodeEnum.ERROR_INSTITUTION_CREATE;
import static com.guigehling.healthcare.enumeration.ErrorCodeEnum.ERROR_INSTITUTION_FIND_BY_ACCESS_KEY;
import static com.guigehling.healthcare.helper.BuilderHelper.institutionBuilder;
import static com.guigehling.healthcare.helper.BuilderHelper.institutionDTOBuilder;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    private final WalletService walletService;

    private final MessageHelper messageHelper;

    public InstitutionDTO create(InstitutionDTO institutionDTO) {
        try {
            var institution = institutionRepository.save(institutionBuilder(institutionDTO));
            var walletDTO = walletService.create(institution.getIdInstitution());

            return institutionDTO
                    .withIdInstitution(institution.getIdInstitution())
                    .withAccessKey(generateAccessKey(institutionDTO.getCnpj()))
                    .withCoin(walletDTO.getCoin());
        } catch (Exception e) {
            log.error(messageHelper.get(ERROR_INSTITUTION_CREATE, e.getMessage()), e);
            throw new BusinessException(INTERNAL_SERVER_ERROR, messageHelper.get(ERROR_INSTITUTION_CREATE, e.getMessage()));
        }
    }

    public InstitutionDTO findInstitution(String accessKey) {
        var institutionDTO = findByAccessKey(accessKey);
        var walletDTO = walletService.getWalletByInstitution(institutionDTO.getIdInstitution());

        return institutionDTO
                .withAccessKey(accessKey)
                .withCoin(walletDTO.getCoin());
    }

    public InstitutionDTO findByAccessKey(String accessKey) {
        var institutionOpt = institutionRepository.findByCnpj(decodeAccessKey(accessKey));

        if (institutionOpt.isPresent())
            return institutionDTOBuilder(institutionOpt.get());

        throw new BusinessException(NOT_FOUND, messageHelper.get(ERROR_INSTITUTION_FIND_BY_ACCESS_KEY));
    }

    private static String generateAccessKey(String cnpj) {
        return new String(Base64.encodeBase64(cnpj.getBytes()));
    }

    private static String decodeAccessKey(String cnpj) {
        return new String(Base64.decodeBase64(cnpj.getBytes()));
    }
}
