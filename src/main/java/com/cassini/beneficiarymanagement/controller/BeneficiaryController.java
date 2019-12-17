package com.cassini.beneficiarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.service.BeneficiaryService;

@RestController
@CrossOrigin
@RequestMapping("/beneficiaries")
public class BeneficiaryController {
	
	@Autowired
	BeneficiaryService beneficiaryService;
	
	@GetMapping("/{customerId}")
	public ResponseEntity<List<Beneficiary>> getAllBeneficiary(@PathVariable("customerId") Integer customerId){
		return new ResponseEntity<>(beneficiaryService.getAllBeneficiary(customerId),HttpStatus.OK);
	}

}
