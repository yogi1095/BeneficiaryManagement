package com.cassini.beneficiarymanagement.service;

import java.util.List;

import com.cassini.beneficiarymanagement.entity.Beneficiary;

public interface BeneficiaryService {
	
	List<Beneficiary> getAllBeneficiary(Integer customerId);

}
