package com.cassini.beneficiarymanagement.service;

import java.util.List;

import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;

public interface BeneficiaryService {
	
	MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto);

	List<Beneficiary> getAllBeneficiary(Integer customerId);

	MessageDto updateBeneficiary(UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto) throws BeneficiaryNotFoundException;

}
