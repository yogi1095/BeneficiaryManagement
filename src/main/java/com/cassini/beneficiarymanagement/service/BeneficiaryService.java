package com.cassini.beneficiarymanagement.service;

import java.util.List;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.entity.Beneficiary;

public interface BeneficiaryService {
	
	MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto);

	List<Beneficiary> getAllBeneficiary(Integer customerId);

}
