package com.guigehling.healthcare.repository;

import com.guigehling.healthcare.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>, JpaSpecificationExecutor<Exam> {

    Optional<Exam> findByIdExamAndIdInstitution(Long idExam, Long idInstitution);

}