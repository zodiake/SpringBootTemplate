package application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import application.exception.NoAuthorityException;
import application.exception.PostNotFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	public static final String NOTFOUND = "error/notFound";
	public static final String NOAUTHORITY = "error/noAuthority";

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(PostNotFoundException.class)
	public String postNotFound() {
		return NOTFOUND;
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(NoAuthorityException.class)
	public String noAuthority() {
		return NOAUTHORITY;
	}
}