package com.cassini.beneficiarymanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.entity.Bank;
import com.cassini.beneficiarymanagement.exception.BankNotFound;
import com.cassini.beneficiarymanagement.repository.BankRepository;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepository;

	@Override
	public Bank getBankDetails(String ifscCode) throws BankNotFound {
		Optional<Bank> bankDetaials =bankRepository.findByIfscCode(ifscCode);
		if(bankDetaials.isPresent()) {
			return bankDetaials.get();
		}else {
			throw new BankNotFound(Constant.BANK_NOT_FOUND);
		}	
	}
}