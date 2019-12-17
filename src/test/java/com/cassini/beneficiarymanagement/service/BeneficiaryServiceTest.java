package com.cassini.beneficiarymanagement.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.repository.BeneficiaryRepository;

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

	@Test
	public void testupdateBeneficiary() throws BeneficiaryNotFoundException {
		UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto = new UpdateBeneficiaryRequestDto();
		updateBeneficiaryRequestDto.setBeneficiaryId(1);
		updateBeneficiaryRequestDto.setBeneficiaryName("yoga");
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.SUCCESS);
		messageDto.setStatusCode(200);
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryId(1);
		beneficiary.setBeneficiaryName("yoga");
		Mockito.when(beneficiaryRepository.findById(1)).thenReturn(Optional.of(beneficiary));
		MessageDto response = beneficiaryServiceImpl.updateBeneficiary(updateBeneficiaryRequestDto);
		assertNotNull(response);
	}

	@Test(expected = BeneficiaryNotFoundException.class)
	public void testupdateBeneficiaryNegative() throws BeneficiaryNotFoundException {
		UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto = new UpdateBeneficiaryRequestDto();
		updateBeneficiaryRequestDto.setBeneficiaryId(1);
		updateBeneficiaryRequestDto.setBeneficiaryName("yoga");
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.SUCCESS);
		messageDto.setStatusCode(200);
		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setBeneficiaryId(1);
		beneficiary.setBeneficiaryName("reddy");
		Mockito.when(beneficiaryRepository.findById(2)).thenReturn(Optional.of(beneficiary));
		MessageDto response = beneficiaryServiceImpl.updateBeneficiary(updateBeneficiaryRequestDto);
		assertNotNull(response);
	}

}
