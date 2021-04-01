package be.willekens.template.api.controllers.controllerexception;

import be.willekens.template.infrastructure.exceptions.*;
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

    @ExceptionHandler(DuplicateItemNameException.class)
    public void duplicateItemName(DuplicateItemNameException duplicateItemNameException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("The user tried to register an item that already exists in our database");
        httpServletResponse.sendError(HttpServletResponse.SC_CONFLICT, duplicateItemNameException.getMessage());
    }

    @ExceptionHandler(InvalidItemNameException.class)
    public void invalidItemName(InvalidItemNameException invalidItemNameException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("The user tried to register an item with an invalid item name");
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, invalidItemNameException.getMessage());
    }

    @ExceptionHandler(InvalidPriceException.class)
    public void invalidPrice(InvalidPriceException invalidPriceException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("The user tried to register an item with an invalid price value");
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, invalidPriceException.getMessage());
    }

    @ExceptionHandler(InvalidStockValue.class)
    public void invalidStockValue(InvalidStockValue invalidStockValue, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("The user tried to register an item with an invalid stock value");
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, invalidStockValue.getMessage());
    }

    @ExceptionHandler(CustomerDoesNotExistException.class)
    public void customerDoesNotExist(CustomerDoesNotExistException customerDoesNotExistException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("The user tried access a customer that does not exist");
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, customerDoesNotExistException.getMessage());
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public void notAuthorized(NotAuthorizedException notAuthorizedException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("The user tried to perform an action without the right permissions");
        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, notAuthorizedException.getMessage());
    }

    @ExceptionHandler(ItemDoesNotExistException.class)
    public void itemDoesNotExist(ItemDoesNotExistException itemDoesNotExistException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("The requested item doesn't exist");
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, itemDoesNotExistException.getMessage());
    }

    @ExceptionHandler(OrderDoesNotExistException.class)
    public void orderDoesNotExist(OrderDoesNotExistException orderDoesNotExistException, HttpServletResponse httpServletResponse) throws IOException {
        logger.error("The requested order doesn't exist");
        httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, orderDoesNotExistException.getMessage());
    }
}
