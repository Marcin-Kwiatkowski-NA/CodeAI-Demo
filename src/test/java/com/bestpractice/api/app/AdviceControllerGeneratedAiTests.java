package com.bestpractice.api.app;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequest.class)
    public ErrorResponse badRequest() {
        ErrorResponse response = new ErrorResponse("Bad Request", HttpStatus.BAD_REQUEST.toString());
        return response;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Conflict.class)
    public ErrorResponse conflict() {
        ErrorResponse response = new ErrorResponse("Conflict", HttpStatus.CONFLICT.toString());
        return response;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(Forbidden.class)
    public ErrorResponse forbidden() {
        ErrorResponse response = new ErrorResponse("Forbidden", HttpStatus.FORBIDDEN.toString());
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFound.class)
    public ErrorResponse notFound() {
        ErrorResponse response = new ErrorResponse("Not Found", HttpStatus.NOT_FOUND.toString());
        return response;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorized.class)
    public ErrorResponse unauthorized() {
        ErrorResponse response = new ErrorResponse("Unauthorized", HttpStatus.UNAUTHORIZED.toString());
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ErrorResponse generalException() {
        ErrorResponse response = new ErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorResponse noHandlerFound() {
        ErrorResponse response = new ErrorResponse("No Handler Found", HttpStatus.NOT_FOUND.toString());
        return response;
    }
}