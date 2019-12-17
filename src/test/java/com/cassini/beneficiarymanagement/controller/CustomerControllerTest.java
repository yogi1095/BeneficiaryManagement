package com.cassini.beneficiarymanagement.controller;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cassini.beneficiarymanagement.dto.LoginRequestDto;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;
<<<<<<< HEAD
@RunWith(MockitoJUnitRunner.class)
=======
import com.cassini.beneficiarymanagement.service.CustomerService;

@RunWith(MockitoJUnitRunner.Silent.class)
>>>>>>> 851e9d7011d6a2103fb4807479dae9724e0a43cd
public class CustomerControllerTest {
	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerService customerService;

	@Test
	public void authenticateCustomerTestPositive() throws UserNotFoundException {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setUserName("priya");
		customer.setPassword("priya");
		LoginRequestDto loginRequestDto = new LoginRequestDto();
		loginRequestDto.setUserName("priya");
		loginRequestDto.setPassword("priya");
		Mockito.when(customerService.authenticateCustomer(loginRequestDto)).thenReturn(customer);
		ResponseEntity<Customer> actual = customerController.authenticateCustomer(loginRequestDto);
		assertEquals(HttpStatus.OK, actual.getStatusCode());
	}
}
