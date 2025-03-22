package br.inatel.dm112.rest.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(DeliveryException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public DeliveryError handlerException(DeliveryException ex) {

		DeliveryError error = new DeliveryError();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public DeliveryError authorization(Exception ex, HttpServletRequest request) {
		return new DeliveryError(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage(),
				System.currentTimeMillis());
	}
}
