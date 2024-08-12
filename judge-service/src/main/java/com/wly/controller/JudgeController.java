package com.wly.controller;

import com.wly.domain.R;
import com.wly.dto.CodeSubmissionDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/competition")
public class JudgeController {

    @PostMapping("/submit")
    public String submitCode(@Valid @RequestBody CodeSubmissionDto codeSubmissionDto){
        return null;
    }
}
