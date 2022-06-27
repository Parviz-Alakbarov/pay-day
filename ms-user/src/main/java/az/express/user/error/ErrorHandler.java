package az.express.user.error;

import az.express.user.error.model.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({DataMismatchException.class})
    public RestErrorResponse handleDataMismatchException(DataMismatchException ex) {
        var errorResponse = RestErrorResponse.builder()
                .code(HttpStatus.CONFLICT.name())
                .message(ex.getErrorMessage())
                .uuid(ex.getErrorUuid())
                .build();

        log.error("Request parameter invalid : {}, code: {}, message: {}",
                errorResponse.getUuid(), errorResponse.getCode(), errorResponse.getMessage());

        return errorResponse;
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({UserNotFoundException.class})
    public RestErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
        var errorResponse = RestErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.name())
                .message(ex.getErrorMessage())
                .uuid(ex.getErrorUuid())
                .build();

        log.error("Request parameter invalid : {}, code: {}, message: {}",
                errorResponse.getUuid(), errorResponse.getCode(), errorResponse.getMessage());

        return errorResponse;
    }

}