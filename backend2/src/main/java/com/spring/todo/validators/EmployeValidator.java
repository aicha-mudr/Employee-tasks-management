package com.spring.todo.validators;

import com.spring.todo.dto.EmployeDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeValidator {

    public static List<String> validateCategory(EmployeDto categoryDto) {
        List<String> errors = new ArrayList<>();
        if (categoryDto == null) {
            errors.add("Please fill the name");
            errors.add("Please fill the description");
            return errors;
        }
        if (StringUtils.isEmpty(categoryDto.getName())) {
            errors.add("Please fill the name");
        }
        if (StringUtils.isEmpty(categoryDto.getFunction())) {
            errors.add("Please fill the description");
        }
        return errors;
    }
        public static List<String> validateEmployeCredentials(String email, String password) {
            List<String> errors = new ArrayList<>();
            if (StringUtils.isEmpty(email)) {
                errors.add("Email is empty");
            }
            if (StringUtils.isEmpty(password)) {
                errors.add("Password is empty");
            }
            return errors;
        }

}
