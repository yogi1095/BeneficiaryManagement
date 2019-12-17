package com.cassini.beneficiary.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.repository.BeneficiaryRepository;
import com.cassini.beneficiarymanagement.service.BeneficiaryServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BeneficiaryServiceTest {

	@InjectMocks
	BeneficiaryServiceImpl beneficiaryServiceImpl;

	@Mock
	BeneficiaryRepository beneficiaryRepository;

	List<Beneficiary> beneficiaries = null;
	Beneficiary beneficiary = null;
	Customer customer = null;

	@Test
	public void getAllBeneficiaryTest() {

		customer = new Customer();
		customer.setCustomerId(1);
		beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryId(11);
		beneficiaries = new ArrayList<>();
		beneficiaries.add(beneficiary);
		Mockito.when(beneficiaryRepository.findAllByCustomerOrderByBeneficiaryNameAsc(customer))
				.thenReturn(beneficiaries);
		List<Beneficiary> actual = beneficiaryServiceImpl.getAllBeneficiary(1);
		assertNotNull(actual);

	}

}
