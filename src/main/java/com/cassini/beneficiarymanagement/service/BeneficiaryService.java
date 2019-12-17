package com.cassini.beneficiarymanagement.service;

import javax.security.auth.login.AccountNotFoundException;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;

public interface BeneficiaryService {
	
	MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto) throws AccountNotFoundException, MaximumBeneficiaryException, UserNotFoundException, BeneficiaryAlreadyExistException;

}
