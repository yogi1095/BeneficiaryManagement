package com.cassini.beneficiarymanagement.controller;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
import com.cassini.beneficiarymanagement.service.BeneficiaryService;

@RestController
@CrossOrigin
@RequestMapping("/beneficiaries")
public class BeneficiaryController {
	@Autowired
	BeneficiaryService beneficiaryService;
	
	
	@PostMapping
	public MessageDto addBeneficiary(@RequestBody AddBeneficiaryRequestDto addBeneficiaryRequestDto) throws AccountNotFoundException, MaximumBeneficiaryException, UserNotFoundException, BeneficiaryAlreadyExistException{
		return beneficiaryService.addBeneficiary(addBeneficiaryRequestDto);
	}

}
