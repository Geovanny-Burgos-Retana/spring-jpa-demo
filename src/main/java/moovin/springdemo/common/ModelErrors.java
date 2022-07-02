package moovin.springdemo.common;

import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

public class ModelErrors {
    public String getModelErrors(org.springframework.validation.Errors errors) {
        StringBuilder exceptionMessage = new StringBuilder();
        // Extract ConstraintViolation list from body errorsasdas
        List<ConstraintViolation<?>> violationsList = new ArrayList<>();
        for (ObjectError e : errors.getAllErrors()) {
            violationsList.add(e.unwrap(ConstraintViolation.class));
        }
        // Construct a helpful message for each input violation
        for (ConstraintViolation<?> violation : violationsList) {
            exceptionMessage.append(violation.getPropertyPath()).append(" ").append(violation.getMessage()).append(" - ");
        }
        return exceptionMessage.toString();
    }
}
