package com.guigehling.healthcare.resource.v1;

import com.guigehling.healthcare.dto.InstitutionDTO;
import com.guigehling.healthcare.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Validated
@RestController
@RequestMapping("/v1/institution")
@RequiredArgsConstructor
public class InstitutionResource {

    private final InstitutionService institutionService;

    @PostMapping
    @ResponseStatus(CREATED)
    public InstitutionDTO create(@RequestBody @Valid InstitutionDTO agendaDTO) {
        return null;
    }

}


