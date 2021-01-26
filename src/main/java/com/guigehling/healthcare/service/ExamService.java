package com.guigehling.healthcare.service;

import com.guigehling.healthcare.dto.ExamDTO;
import com.guigehling.healthcare.dto.WalletDTO;
import com.guigehling.healthcare.exception.BusinessException;
import com.guigehling.healthcare.helper.MessageHelper;
import com.guigehling.healthcare.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.guigehling.healthcare.enumeration.ErrorCodeEnum.ERROR_EXAM_CREATE_INSUFFICIENT_FUNDS;
import static com.guigehling.healthcare.helper.BuilderHelper.examDTOBuilder;
import static com.guigehling.healthcare.helper.BuilderHelper.newExamBuilder;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {

    private static final BigDecimal EXAM_COST = BigDecimal.ONE;

    private final ExamRepository examRepository;

    private final InstitutionService institutionService;
    private final WalletService walletService;

    private final MessageHelper messageHelper;

    public ExamDTO create(String accessKey, ExamDTO examDTO) {
        var institutionDTO = institutionService.findByAccessKey(accessKey);
        var walletDTO = walletService.getWalletByInstitution(institutionDTO.getIdInstitution());

        if (!hasEnoughBalance(walletDTO))
            throw new BusinessException(BAD_REQUEST,
                    messageHelper.get(ERROR_EXAM_CREATE_INSUFFICIENT_FUNDS, String.valueOf(examDTO.getIdInstitution())));

        var exam = examRepository.save(newExamBuilder(examDTO));
        chargeExamCreationFee(walletDTO);

        return examDTOBuilder(exam);
    }

    private boolean hasEnoughBalance(WalletDTO walletDTO) {
        return walletDTO.getCoin().compareTo(EXAM_COST) >= 0;
    }

    private void chargeExamCreationFee(WalletDTO walletDTO) {
        walletService.update(walletDTO.withCoin(walletDTO.getCoin().subtract(EXAM_COST)));
    }

}
