package com.guigehling.healthcare.resource.v1;

import com.guigehling.healthcare.dto.ExamDTO;
import com.guigehling.healthcare.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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

    @GetMapping
    public ExamDTO findById(@RequestHeader String accessKey, @PathParam("idExam") Long idExam) {
        return examService.findById(accessKey, idExam);
    }

}


