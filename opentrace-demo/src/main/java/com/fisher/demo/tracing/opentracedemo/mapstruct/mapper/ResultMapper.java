package com.fisher.demo.tracing.opentracedemo.mapstruct.mapper;

import com.fisher.demo.tracing.opentracedemo.entity.ValidateResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.FieldError;

@Mapper(componentModel = "spring")
public interface ResultMapper {

    ResultMapper INSTANCE = Mappers.getMapper(ResultMapper.class);

    @Mapping(target = "error", /*source = "objectName"*/ expression = "java(java.lang.String.valueOf(fieldError.getDefaultMessage()))")
    ValidateResult mapperResult(FieldError fieldError);

}
