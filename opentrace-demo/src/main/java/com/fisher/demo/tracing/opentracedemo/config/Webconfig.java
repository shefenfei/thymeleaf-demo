package com.fisher.demo.tracing.opentracedemo.config;

import com.fisher.demo.tracing.opentracedemo.validator.UserRequestValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.validation.Validator;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class Webconfig extends WebMvcConfigurationSupport {

    //全局配置validator
    @Override
    protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer(FormattingConversionService mvcConversionService, Validator mvcValidator) {
        ConfigurableWebBindingInitializer configurableWebBindingInitializer = super.getConfigurableWebBindingInitializer(mvcConversionService, mvcValidator);
        configurableWebBindingInitializer.setValidator(new UserRequestValidator());
        return configurableWebBindingInitializer;
    }
}
