package com.guigehling.healthcare.helper;

import com.guigehling.healthcare.dto.ExamDTO;
import com.guigehling.healthcare.dto.InstitutionDTO;
import com.guigehling.healthcare.dto.WalletDTO;
import com.guigehling.healthcare.entity.Exam;
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

    public static Exam newExamBuilder(ExamDTO examDTO) {
        var exam = examBuilder(examDTO);
        return exam.withConsulted(false);
    }

    public static Exam examBuilder(ExamDTO examDTO) {
        return Exam.builder()
                .idInstitution(examDTO.getIdInstitution())
                .patientAge(examDTO.getPatientAge())
                .patientGender(examDTO.getPatientGender())
                .patientName(examDTO.getPatientName())
                .physicianCrm(examDTO.getPhysicianCrm())
                .physicianName(examDTO.getPhysicianName())
                .procedureName(examDTO.getProcedureName())
                .build();
    }

    public static ExamDTO examDTOBuilder(Exam exam) {
        return ExamDTO.builder()
                .idExam(exam.getIdExam())
                .idInstitution(exam.getIdInstitution())
                .patientAge(exam.getPatientAge())
                .patientGender(exam.getPatientGender())
                .patientName(exam.getPatientName())
                .physicianCrm(exam.getPhysicianCrm())
                .physicianName(exam.getPhysicianName())
                .procedureName(exam.getProcedureName())
                .build();
    }

    public static Wallet walletBuilder(Long idInstitution, BigDecimal coin) {
        return Wallet.builder()
                .idInstitution(idInstitution)
                .coin(coin)
                .build();
    }

    public static Wallet walletBuilder(WalletDTO walletDTO) {
        return Wallet.builder()
                .idWallet(walletDTO.getIdWallet())
                .idInstitution(walletDTO.getIdInstitution())
                .coin(walletDTO.getCoin())
                .build();
    }

    public static WalletDTO walletDTOBuilder(Wallet wallet) {
        return WalletDTO.builder()
                .idWallet(wallet.getIdWallet())
                .idInstitution(wallet.getIdInstitution())
                .coin(wallet.getCoin())
                .build();
    }
}
