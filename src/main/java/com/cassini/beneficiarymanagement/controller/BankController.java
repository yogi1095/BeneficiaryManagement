package com.cassini.beneficiarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.beneficiarymanagement.entity.Bank;
import com.cassini.beneficiarymanagement.exception.BankNotFound;
import com.cassini.beneficiarymanagement.service.BankService;

@RestController
@CrossOrigin
@RequestMapping("/banks")
public class BankController {
	
	@Autowired
	private BankService bankService;
	

	@GetMapping("/{ifscCode}")
	public ResponseEntity<Bank> getBankDetails(@PathVariable("ifscCode") String ifscCode) throws BankNotFound {
		return ResponseEntity.ok().body(bankService.getBankDetails(ifscCode));

	}

}
