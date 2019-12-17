package com.cassini.beneficiarymanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import javax.security.auth.login.AccountNotFoundException;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;

@Service
public interface BeneficiaryService {

	MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto) throws AccountNotFoundException,
			MaximumBeneficiaryException, UserNotFoundException, BeneficiaryAlreadyExistException;

	List<Beneficiary> getAllBeneficiary(Integer customerId);

	MessageDto deleteAccounts(Integer beneficiaryId) throws BeneficiaryNotFoundException;


}
