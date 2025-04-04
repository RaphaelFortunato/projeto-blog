package com.example.demo.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//essa anotação faz com o que Spring fique sempre 'escutando' essa class
public class GlobalExceptionHandler {

	@ExceptionHandler(RecursoNaoEncntradoException.class)
	public ResponseEntity<Object> handleRecursoNaoEncontrado(RecursoNaoEncntradoException ex){
		Map<String, Object> corpo = new LinkedHashMap<>();
		corpo.put("timestamp", LocalDateTime.now());
		corpo.put("status", HttpStatus.NOT_FOUND.value());
		corpo.put("error", "Recurso não encontrado");
		corpo.put("message", ex.getMessage());
		
		return new ResponseEntity<>(corpo, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericoException(RecursoNaoEncntradoException ex){
		Map<String, Object> corpo = new LinkedHashMap<>();
		corpo.put("timestamp", LocalDateTime.now());
		corpo.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		corpo.put("error", "Recurso não encontrado");
		corpo.put("message", ex.getMessage());
		
		return new ResponseEntity<>(corpo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
