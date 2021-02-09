package com.fisher.demo.tracing.opentracedemo.controller;

import com.fisher.demo.tracing.opentracedemo.entity.ValidateResult;
import com.fisher.demo.tracing.opentracedemo.mapstruct.mapper.ResultMapper;
import com.fisher.demo.tracing.opentracedemo.request.UserRequestForm;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/validate")
public class ValidatorController {

//    @InitBinder
//    public void validate(WebDataBinder binder) {
//        binder.addValidators();
//        binder.setValidator(new UserRequestValidator());
//    }


    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@Validated @RequestBody UserRequestForm userRequestForm, BindingResult bindingResult) {
        return ResponseEntity.ok(buildErrorResult(bindingResult));
    }


    public List<ValidateResult> buildErrorResult(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ValidateResult> results = fieldErrors.stream()
                .map(ResultMapper.INSTANCE::mapperResult)
                .collect(Collectors.toList());
        return results;
    }

}
