package com.cassini.beneficiarymanagement.controller;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
import com.cassini.beneficiarymanagement.service.BeneficiaryService;

@RestController
@CrossOrigin
@RequestMapping("/beneficiaries")
public class BeneficiaryController {

	@Autowired
	BeneficiaryService beneficiaryService;

	@GetMapping("/{customerId}")
	public ResponseEntity<List<Beneficiary>> getAllBeneficiary(@PathVariable("customerId") Integer customerId) {
		return new ResponseEntity<>(beneficiaryService.getAllBeneficiary(customerId), HttpStatus.OK);
	}

	@PostMapping
	public MessageDto addBeneficiary(@RequestBody AddBeneficiaryRequestDto addBeneficiaryRequestDto)
			throws AccountNotFoundException, MaximumBeneficiaryException, UserNotFoundException,
			BeneficiaryAlreadyExistException {
		return beneficiaryService.addBeneficiary(addBeneficiaryRequestDto);
	}
	
	@PutMapping
	public MessageDto updateBeneficiary(@RequestBody UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto) throws BeneficiaryNotFoundException {
		
		return beneficiaryService.updateBeneficiary(updateBeneficiaryRequestDto);
	}

}
