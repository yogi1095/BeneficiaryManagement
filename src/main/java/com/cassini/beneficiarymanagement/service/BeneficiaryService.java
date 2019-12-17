package com.cassini.beneficiarymanagement.service;

import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;

@Service
public interface BeneficiaryService {

	MessageDto deleteAccounts(Integer beneficiaryId) throws BeneficiaryNotFoundException;


}
