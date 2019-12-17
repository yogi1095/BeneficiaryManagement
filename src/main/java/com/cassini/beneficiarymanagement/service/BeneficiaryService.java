package com.cassini.beneficiarymanagement.service;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;

public interface BeneficiaryService {
	
	MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto);

}
