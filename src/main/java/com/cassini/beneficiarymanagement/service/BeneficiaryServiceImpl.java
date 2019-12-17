package com.cassini.beneficiarymanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.entity.Account;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
import com.cassini.beneficiarymanagement.repository.AccountRepository;
import com.cassini.beneficiarymanagement.repository.BeneficiaryRepository;
import com.cassini.beneficiarymanagement.repository.CustomerRepository;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Beneficiary> getAllBeneficiary(Integer customerId) {
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		List<Beneficiary> beneficiaries = beneficiaryRepository.findAllByCustomerOrderByBeneficiaryNameAsc(customer);
		if (beneficiaries.isEmpty()) {
			return new ArrayList<>();
		} else {
			return beneficiaries;
		}
	}

	@Autowired
	AccountRepository accountRepository;

	@Override
	public MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto) {
		Beneficiary beneficiary = new Beneficiary();
		Optional<Customer> customer = customerRepository.findById(addBeneficiaryRequestDto.getCustomerId());
		Optional<Account> account = accountRepository.findById(addBeneficiaryRequestDto.getBeneficiaryAccountNumber());
		beneficiary.setBeneficiaryAccount(account.get());
		beneficiary.setBeneficiaryName(addBeneficiaryRequestDto.getBeneficiaryName());
		beneficiary.setCustomer(customer.get());
		beneficiaryRepository.save(beneficiary);
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.SUCCESS);
		messageDto.setStatusCode(HttpStatus.OK.value());
		return messageDto;
	}

	@Override
	public MessageDto updateBeneficiary(UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto)
			throws BeneficiaryNotFoundException {
		Optional<Beneficiary> beneficiary = beneficiaryRepository
				.findById(updateBeneficiaryRequestDto.getBeneficiaryId());
		if (beneficiary.isPresent()) {
			beneficiary.get().setBeneficiaryName(updateBeneficiaryRequestDto.getBeneficiaryName());
			beneficiaryRepository.save(beneficiary.get());
			MessageDto messageDto = new MessageDto();
			messageDto.setMessage(Constant.SUCCESS);
			messageDto.setStatusCode(HttpStatus.OK.value());
			return messageDto;
		} else {

			throw new BeneficiaryNotFoundException(Constant.BENEFICIARY_NOT_FOUND);
		}
	}

}
