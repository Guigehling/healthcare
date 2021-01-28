package com.guigehling.healthcare.service;

import com.guigehling.healthcare.dto.ExamDTO;
import com.guigehling.healthcare.dto.WalletDTO;
import com.guigehling.healthcare.entity.Exam;
import com.guigehling.healthcare.exception.BusinessException;
import com.guigehling.healthcare.helper.MessageHelper;
import com.guigehling.healthcare.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static com.guigehling.healthcare.enumeration.ErrorCodeEnum.*;
import static com.guigehling.healthcare.helper.BuilderHelper.examDTOBuilder;
import static com.guigehling.healthcare.helper.BuilderHelper.newExamBuilder;
import static java.lang.Boolean.TRUE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        var idInstitution = getInstitutionByAcessKey(accessKey);
        var walletDTO = walletService.getWalletByInstitution(idInstitution);

        if (hasInsufficientBalance(walletDTO))
            throw new BusinessException(BAD_REQUEST, messageHelper.get(ERROR_EXAM_CREATE_INSUFFICIENT_FUNDS));

        var exam = examRepository.save(newExamBuilder(examDTO.withIdInstitution(idInstitution)));
        chargeExam(walletDTO);

        return examDTOBuilder(exam);
    }

    public ExamDTO findById(String accessKey, Long idExam) {
        var idInstitution = getInstitutionByAcessKey(accessKey);
        var examOpt = examRepository.findByIdExamAndIdInstitution(idExam, idInstitution);
        var walletDTO = walletService.getWalletByInstitution(idInstitution);

        if (examOpt.isEmpty())
            throw new BusinessException(NOT_FOUND, messageHelper.get(ERROR_EXAM_FIND_BY_ID, String.valueOf(idExam)));

        if (!examOpt.get().isConsulted())
            chargeExamAccessed(walletDTO);

        return examDTOBuilder(examRepository.save(examOpt.get().withConsulted(true)));
    }

    public ExamDTO update(String accessKey, ExamDTO examDTO) {
        var idInstitution = getInstitutionByAcessKey(accessKey);
        var examOpt = examRepository.findByIdExamAndIdInstitution(examDTO.getIdExam(), idInstitution);

        if (examOpt.isEmpty())
            throw new BusinessException(NOT_FOUND, messageHelper.get(ERROR_EXAM_FIND_BY_ID, String.valueOf(examDTO.getIdExam())));

        var exam = examRepository.save(Exam.builder()
                .idExam(examOpt.get().getIdExam())
                .idInstitution(idInstitution)
                .patientAge(examDTO.getPatientAge())
                .patientName(examDTO.getPatientName())
                .patientGender(examDTO.getPatientGender())
                .physicianName(examDTO.getPhysicianName())
                .physicianCrm(examDTO.getPhysicianCrm())
                .procedureName(examDTO.getProcedureName())
                .consulted(examOpt.get().isConsulted())
                .build());

        return examDTOBuilder(exam);
    }

    public Map<String, Boolean> delete(String accessKey, Long idExam) {
        var idInstitution = getInstitutionByAcessKey(accessKey);
        var examOpt = examRepository.findByIdExamAndIdInstitution(idExam, idInstitution);

        if (examOpt.isEmpty())
            throw new BusinessException(NOT_FOUND, messageHelper.get(ERROR_EXAM_FIND_BY_ID, String.valueOf(idExam)));

        examRepository.delete(examOpt.get());
        return Collections.singletonMap("deleted", TRUE);
    }

    public boolean hasInsufficientBalance(WalletDTO walletDTO) {
        return walletDTO.getCoin().compareTo(EXAM_COST) < 0;
    }

    public void chargeExamAccessed(WalletDTO walletDTO) {
        if (hasInsufficientBalance(walletDTO))
            throw new BusinessException(BAD_REQUEST, messageHelper.get(ERROR_EXAM_FIND_INSUFFICIENT_FUNDS));

        chargeExam(walletDTO);
    }

    public void chargeExam(WalletDTO walletDTO) {
        walletService.update(walletDTO.withCoin(walletDTO.getCoin().subtract(EXAM_COST)));
    }

    private Long getInstitutionByAcessKey(String accessKey) {
        return institutionService.findByAccessKey(accessKey).getIdInstitution();
    }

}
