package com.eCommerce.eCommerceOrder.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> handleConstraintViolationException(ConstraintViolationException e){
        List<String> error  = new ArrayList<>();

        e.getConstraintViolations().forEach( constraintViolation ->{
            error.add(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
        });

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleException(ItemNotFoundException e){
        
		log.error("ItemNotFoundException");

        return new ResponseEntity<>("ItemNotFoundException",HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleException(OrderNotFoundException e){
        
		log.error("OrderNotFoundException");

        return new ResponseEntity<>("OrderNotFoundException",HttpStatus.NOT_FOUND);
    }
	
	
	
}
