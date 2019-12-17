package com.cassini.beneficiarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.service.BeneficiaryService;

@RestController
@CrossOrigin
@RequestMapping("/beneficiaries")
public class BeneficiaryController {
	@Autowired
	BeneficiaryService beneficiaryService;
	
	@PostMapping
	public MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto) {
		return beneficiaryService.addBeneficiary(addBeneficiaryRequestDto);
	}

}
