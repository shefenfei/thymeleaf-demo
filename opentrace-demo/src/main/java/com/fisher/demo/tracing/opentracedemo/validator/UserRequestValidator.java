package com.fisher.demo.tracing.opentracedemo.validator;

import com.fisher.demo.tracing.opentracedemo.request.UserRequestForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRequestForm.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.not allow empty", "name.not allow empty");
        UserRequestForm requestForm = (UserRequestForm) obj;
        if (requestForm.getAge() > 100) {
            errors.rejectValue("age", "4000", "有这么大岁数 ？");
        }
    }
}
