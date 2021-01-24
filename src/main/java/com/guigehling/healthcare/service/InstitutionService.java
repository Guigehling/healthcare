package com.guigehling.healthcare.service;

import com.guigehling.healthcare.dto.InstitutionDTO;
import com.guigehling.healthcare.exception.BusinessException;
import com.guigehling.healthcare.helper.MessageHelper;
import com.guigehling.healthcare.repository.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.guigehling.healthcare.enumeration.ErrorCodeEnum.ERROR_INSTITUTION_CREATE;
import static com.guigehling.healthcare.helper.BuilderHelper.institutionBuilder;
import static com.guigehling.healthcare.helper.BuilderHelper.institutionDTOBuilder;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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
            walletService.create(institution.getIdInstitution());

            return institutionDTOBuilder(institution);
        } catch (Exception e) {
            log.error(messageHelper.get(ERROR_INSTITUTION_CREATE, e.getMessage()), e);
            throw new BusinessException(INTERNAL_SERVER_ERROR, messageHelper.get(ERROR_INSTITUTION_CREATE, e.getMessage()));
        }
    }
}
