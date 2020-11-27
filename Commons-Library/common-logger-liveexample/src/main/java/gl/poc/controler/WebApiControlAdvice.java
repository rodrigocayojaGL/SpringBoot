package gl.poc.controler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import gl.poc.model.Error;

@RestControllerAdvice
public class WebApiControlAdvice {

	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity<Error> handleThrowable(Throwable e) {

		return new ResponseEntity<>(
				new Error(e.getClass().getSimpleName(), e.getMessage(), LocalDateTime.now()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
