package com.cassini.beneficiarymanagement.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.dto.MessageDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<MessageDto> accountNotFoundException(){
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.ACCOUNT_NOT_FOUND);
		messageDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageDto);
	}
	

	@ExceptionHandler(BankNotFound.class)
	public ResponseEntity<MessageDto> bankNotFound(){
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.BANK_NOT_FOUND);
		messageDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageDto);
	}
	
	@ExceptionHandler(MaximumBeneficiaryException.class)
	public ResponseEntity<MessageDto> maximumBeneficiaryException(){
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.MAX_BENEFICIARY);
		messageDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageDto);
	}


}
