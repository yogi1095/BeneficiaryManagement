package com.cassini.beneficiarymanagement.controller;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.cassini.beneficiarymanagement.constants.Constant;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.dto.UpdateBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.BeneficiaryNotFoundException;
import com.cassini.beneficiarymanagement.service.BeneficiaryService;


@RunWith(MockitoJUnitRunner.class)
public class BeneficiaryControllerTest {

	@InjectMocks
	BeneficiaryController beneficiaryController;

	@Mock
	BeneficiaryService beneficiaryService;

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
		Mockito.when(beneficiaryService.getAllBeneficiary(1)).thenReturn(beneficiaries);
		ResponseEntity<List<Beneficiary>> actual = beneficiaryController.getAllBeneficiary(1);
		assertNotNull(actual);
	}

	@Test
	public void testDeleteBeneficiary() throws BeneficiaryNotFoundException {
		MessageDto messageDto=new MessageDto();
		Mockito.when(beneficiaryService.deleteBeneficiary(1)).thenReturn(messageDto);
		ResponseEntity<MessageDto> response= beneficiaryController.deleteBeneficiary(1);
	}
	public void updateBeneficiary() throws BeneficiaryNotFoundException {
		UpdateBeneficiaryRequestDto updateBeneficiaryRequestDto = new UpdateBeneficiaryRequestDto();
		updateBeneficiaryRequestDto.setBeneficiaryId(1);
		updateBeneficiaryRequestDto.setBeneficiaryName("yoga");
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.SUCCESS);
		messageDto.setStatusCode(200);
		Mockito.when(beneficiaryService.updateBeneficiary(updateBeneficiaryRequestDto)).thenReturn(messageDto);
		MessageDto response = beneficiaryController.updateBeneficiary(updateBeneficiaryRequestDto);
		assertNotNull(response);
	}

}
