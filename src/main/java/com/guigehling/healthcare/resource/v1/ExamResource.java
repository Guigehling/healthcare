package com.guigehling.healthcare.resource.v1;

import com.guigehling.healthcare.dto.ExamDTO;
import com.guigehling.healthcare.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@Validated
@RestController
@RequestMapping("/v1/exam")
@RequiredArgsConstructor
public class ExamResource {

    private final ExamService examService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ExamDTO create(@RequestHeader String accessKey, @RequestBody @Valid ExamDTO examDTO) {
        return examService.create(accessKey, examDTO);
    }

    @GetMapping("/{idExam}")
    public ExamDTO findById(@RequestHeader String accessKey, @PathVariable("idExam") @Positive Long idExam) {
        return examService.findById(accessKey, idExam);
    }

    @PutMapping
    public ExamDTO update(@RequestHeader String accessKey, @RequestBody @Valid ExamDTO examDTO) {
        return examService.update(accessKey, examDTO);
    }

    @DeleteMapping("/{idExam}")
    public Map<String, Boolean> delete(@RequestHeader String accessKey, @PathVariable("idExam") @Positive Long idExam) {
        return examService.delete(accessKey, idExam);
    }

}


