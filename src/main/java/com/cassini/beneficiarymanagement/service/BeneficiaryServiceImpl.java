package com.cassini.beneficiarymanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryServiceImpl.class);
	
	/**
	 * This will inject all the implementations in the repository classes
	 */

	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	AccountRepository accountRepository;
	
	/**
	 * This API is used to list all the beneficiary details by passing the customerId
	 * 
	 * By passing the customerId as a pathvariable the beneficiaries of current 
	 * customer can be listed which includes both customer details and the beneficiary details
	 * 
	 * This returns the list of beneficiaries
	 */


	@Override
	public List<BeneficiaryListDto> getAllBeneficiary(Integer customerId) {
		logger.info("getting all beneficiaries");
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
	
	/**
	 * This API is used to add the beneficiary by passing the addBeneficiaryRequestDto
	 * 
	 * addBeneficiaryRequestDto is a requestDto which includes 
	 * customerId,beneficiaryName,beneficiaryAccountNumber and ifsc code
	 * 
	 * This returns the new beneficiary details
	 * 
	 * AccountNotFoundException .This exception occurs when account does not found
	 * 
	 * It throws MaximumBeneficiaryException. This exception occurs when beneficiary list 
	 * exceeds the limit of 10 beneficiaries
	 * 
	 * It throws UserNotFoundException .This exception occurs when user does not found while adding beneficiary
	 * It throws BeneficiaryAlreadyExistException. This exception occurs when beneficiary already exists while adding new one
	 */

	@Override
	public MessageDto addBeneficiary(AddBeneficiaryRequestDto addBeneficiaryRequestDto) throws AccountNotFoundException,
			MaximumBeneficiaryException, UserNotFoundException, BeneficiaryAlreadyExistException {
		logger.info("adding beneficiary...");
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
	/**
	 * This API is used to delete the beneficiary by passing the beneficiaryId
	 * 
	 * By passing the beneficiaryId the unwanted beneficiary can be deleted
	 *  
	 * This returns the messageDto which includes message and statuscode
	 * It throws BeneficiaryNotFoundException This exception occurs when beneficiary does not found
	 */
	

	@Override
	public MessageDto deleteBeneficiary(Integer beneficiaryId) throws BeneficiaryNotFoundException {
		logger.info("deleting the beneficiary...");
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
	/**
	 * This API is used to update the beneficiary details by passing the updateBeneficiaryRequestDto
	 * 
	 * updateBeneficiaryRequestDto includes beneficiaryName and beneficiaryId 
	 * This returns the new edited beneficiary details
	 * 
	 * It throws BeneficiaryNotFoundException. This exception occurs when
	 *  beneficiary does not found while editing the beneficiaryDetails
	 */

	@Override
	public MessageDto updateBeneficiary(UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto)
			throws BeneficiaryNotFoundException {
		logger.info("updating beneficiary details...");
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
