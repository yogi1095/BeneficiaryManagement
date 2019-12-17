package com.cassini.beneficiarymanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;

@Service
public interface BeneficiaryService {
	
	MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto);

	List<Beneficiary> getAllBeneficiary(Integer customerId);

	MessageDto deleteAccounts(Integer beneficiaryId) throws BeneficiaryNotFoundException;


}
