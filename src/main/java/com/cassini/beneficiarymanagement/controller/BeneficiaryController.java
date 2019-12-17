package com.cassini.beneficiarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.service.BeneficiaryService;

@RestController
@CrossOrigin
@RequestMapping("/beneficiaries")
public class BeneficiaryController {

	@Autowired
	BeneficiaryService beneficiaryService;

	@DeleteMapping("/{beneficiaryId}")
	public ResponseEntity<MessageDto> deleteAccount(@PathVariable("beneficiaryId") Integer beneficiaryId)
			throws BeneficiaryNotFoundException {

		// logger.info("inside the deleteAccount method in controller..");
		return ResponseEntity.ok().body(beneficiaryService.deleteAccounts(beneficiaryId));

	}

}
