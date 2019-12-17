package com.cassini.beneficiarymanagement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cassini.beneficiarymanagement.dto.LoginRequestDto;
import com.cassini.beneficiarymanagement.entity.Customer;
import com.cassini.beneficiarymanagement.exception.UserNotFoundException;

public class CustomerControllerTest {
	@InjectMocks
	CustomerController customerController;

	@Mock
	com.cassini.beneficiarymanagement.service.CustomerService customerService;

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
