package com.cassini.beneficiarymanagement.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.cassini.beneficiarymanagement.repository.CustomerRepository;

public class CustomerServiceTest {

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Mock
	CustomerRepository customerRepository;

}
