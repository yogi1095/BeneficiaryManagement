package com.cassini.beneficiarymanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.repository.AccountRepository;
import com.cassini.beneficiarymanagement.repository.BeneficiaryRepository;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	@Autowired
	BeneficiaryRepository beneficiaryRepository;
	@Autowired
	AccountRepository accountRepository;

	

	@Override
	public MessageDto deleteAccounts(Integer beneficiaryId) throws BeneficiaryNotFoundException {
		MessageDto messageDto = new MessageDto();
		Optional<Beneficiary> beneficiary = beneficiaryRepository.findByBeneficiaryId(beneficiaryId);
		if (!beneficiary.isPresent())
			throw new BeneficiaryNotFoundException(Constant.BENEFICIARY_NOT_FOUND);
		beneficiary.get().setStatus("Inactive");
		beneficiaryRepository.save(beneficiary.get());
		messageDto.setMessage(Constant.SUCCESS);
		messageDto.setStatusCode(Constant.BENEFICIARY_DELETED);
		return messageDto;
	}
	
}
