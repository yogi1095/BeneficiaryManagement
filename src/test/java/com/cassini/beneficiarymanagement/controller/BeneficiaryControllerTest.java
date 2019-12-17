package com.cassini.beneficiarymanagement.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.cassini.beneficiarymanagement.controller.BeneficiaryController;
import com.cassini.beneficiarymanagement.dto.AddBeneficiaryRequestDto;
import com.cassini.beneficiarymanagement.dto.BeneficiaryListDto;
import com.cassini.beneficiarymanagement.dto.MessageDto;
import com.cassini.beneficiarymanagement.entity.Beneficiary;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.BeneficiaryAlreadyExistException;
import com.cassini.beneficiarymanagement.exception.MaximumBeneficiaryException;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
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
		Mockito.when(beneficiaryService.getAllBeneficiary(1)).thenReturn(new ArrayList<BeneficiaryListDto>());
		assertNotNull(beneficiaryController.getAllBeneficiary(1));
	}
	
	@Test
	public void addBeneficieries() throws AccountNotFoundException, MaximumBeneficiaryException, UserNotFoundException, BeneficiaryAlreadyExistException {
		Mockito.when(beneficiaryService.addBeneficiary(Mockito.any())).thenReturn(new MessageDto());
		assertNotNull(beneficiaryController.addBeneficiary(new AddBeneficiaryRequestDto()));
	}

}
