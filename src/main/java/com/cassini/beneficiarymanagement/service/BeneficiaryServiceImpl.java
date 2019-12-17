package com.cassini.beneficiarymanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;
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
		if(beneficiaries.isEmpty()) {
			return new ArrayList<>();
		}else {
			return beneficiaries;
		}
	}
	
	

}
