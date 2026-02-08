package rooms.backend.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rooms.backend.exceptions.AlreadyExistsException;
import rooms.backend.exceptions.NotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    private ResponseEntity<RestErrorMessage> userAlreadyExistsHandler(AlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestErrorMessage(exception.getMessage(), HttpStatus.CONFLICT));
    }
}
