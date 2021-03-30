package be.willekens.template.api.controllers.controllerexception;

import be.willekens.template.infrastructure.exceptions.DuplicateEmailException;
import be.willekens.template.infrastructure.exceptions.InvalidEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptions {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptions.class);

    @ExceptionHandler(InvalidEmailException.class)
    public void invalidEmail(InvalidEmailException invalidEmailException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("The user tried to register a customer account with an invalid e-mail", invalidEmailException);
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, invalidEmailException.getMessage());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public void duplicateEmail(DuplicateEmailException duplicateEmailException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("The user tried to register an e-mail that already exists in our database", duplicateEmailException);
        httpServletResponse.sendError(HttpServletResponse.SC_CONFLICT, duplicateEmailException.getMessage());
    }
}
