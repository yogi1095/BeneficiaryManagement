package com.cassini.beneficiarymanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.BeneficiaryListDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.entity.Account;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
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
	@Autowired
	AccountRepository accountRepository;

	@Override
	public List<BeneficiaryListDto> getAllBeneficiary(Integer customerId) {
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		List<Beneficiary> beneficiaries = beneficiaryRepository.findAllByCustomerOrderByBeneficiaryNameAsc(customer);
		List<BeneficiaryListDto> beneficiaryListDtos = new ArrayList<>();
		beneficiaries.forEach(beneficiarie -> {
			BeneficiaryListDto beneficiaryListDto = new BeneficiaryListDto();
			beneficiaryListDto.setAccountNumber(beneficiarie.getBeneficiaryAccount().getAccountNumber());
			beneficiaryListDto.setBankName(beneficiarie.getBeneficiaryAccount().getBank().getBankName());
			beneficiaryListDto.setBeneficiaryName(beneficiarie.getBeneficiaryName());
			beneficiaryListDto.setBranchName(beneficiarie.getBeneficiaryAccount().getBank().getBranchName());
			beneficiaryListDto.setBeneficiaryId(beneficiarie.getBeneficiaryId());
			beneficiaryListDtos.add(beneficiaryListDto);
		});
		return beneficiaryListDtos;

	}

	@Override
	public MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto) throws AccountNotFoundException,
			MaximumBeneficiaryException, UserNotFoundException, BeneficiaryAlreadyExistException {
		Beneficiary beneficiary = new Beneficiary();
		Optional<Customer> customer = customerRepository.findById(addBeneficiaryRequestDto.getCustomerId());
		Optional<Account> account = accountRepository.findById(addBeneficiaryRequestDto.getBeneficiaryAccountNumber());

		if (!customer.isPresent()) {
			throw new UserNotFoundException(Constant.USER_NOT_FOUND);
		} else if (beneficiaryRepository.countByCustomer(customer.get()) > Constant.MAX_BENEFICIARY_COUNT) {
			throw new MaximumBeneficiaryException(Constant.MAX_BENEFICIARY);
		} else if (!account.isPresent()) {
			throw new AccountNotFoundException(Constant.ACCOUNT_NOT_FOUND);
		} else if (beneficiaryRepository.findByBeneficiaryAccountAndCustomer(account.get(), customer.get())
				.isPresent()) {
			throw new BeneficiaryAlreadyExistException(Constant.BENEFICIARY_ALREADY_EXIST);

		} else {
			beneficiary.setBeneficiaryAccount(account.get());
			beneficiary.setBeneficiaryName(addBeneficiaryRequestDto.getBeneficiaryName());
			beneficiary.setCustomer(customer.get());
			beneficiary.setStatus(Constant.ACTIVE);
			beneficiaryRepository.save(beneficiary);
			MessageDto messageDto = new MessageDto();
			messageDto.setMessage(Constant.SUCCESS);
			messageDto.setStatusCode(HttpStatus.OK.value());
			return messageDto;
		}
	}

	@Override
	public MessageDto deleteBeneficiary(Integer beneficiaryId) throws BeneficiaryNotFoundException {
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
