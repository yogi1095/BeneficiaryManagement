package com.cassini.beneficiarymanagement.controller;

<<<<<<<HEAD

import javax.security.auth.login.AccountNotFoundException;=======
import java.util.List;>>>>>>>5ef 05e69 b9879301fa7fe523637f3d362f1f1db2

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;<<<<<<<HEAD
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;

import com.cassini.beneficiarymanagement.entity.Beneficiary;
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

}
