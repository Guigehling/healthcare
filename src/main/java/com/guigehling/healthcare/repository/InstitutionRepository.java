package com.guigehling.healthcare.repository;

import com.guigehling.healthcare.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long>, JpaSpecificationExecutor<Institution> {

    Optional<Institution> findByCnpj(String cnpj);

}