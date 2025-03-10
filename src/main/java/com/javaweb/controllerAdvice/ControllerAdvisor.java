package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.customexception.FieldRequiredExeption;
import com.javaweb.model.ErrorResponseDTO;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FieldRequiredExeption.class)
	public ResponseEntity<Object> handleArgumentNotValid(FieldRequiredExeption ex, WebRequest request) {

		ErrorResponseDTO errorResponse = new ErrorResponseDTO();
		errorResponse.setError(ex.getMessage());
		List<String> details = new ArrayList<String>();
		details.add("Số nhà hoặc tên đang bị bỏ trống!!");
		errorResponse.setDetails(details);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_GATEWAY);
	}

}
