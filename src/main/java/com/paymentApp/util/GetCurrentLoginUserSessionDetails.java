package com.paymentApp.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.paymentApp.module.Bank;
import com.paymentApp.module.CurrentUserSession;
import com.paymentApp.module.Customer;
import com.paymentApp.module.Wallet;
import com.paymentApp.repository.CustomerDAO;
import com.paymentApp.repository.SessionDAO;

@Component
public class GetCurrentLoginUserSessionDetails {

	@Autowired
	private SessionDAO sessionDAO;

	@Autowired
	private CustomerDAO customerDAO;
	
	public CurrentUserSession getCurrentUserSession(String key) {
		Optional<CurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		return optional.get();
	}
	
	public Integer getCurrentUserSessionId(String key) {
		Optional<CurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		return optional.get().getId();
	}
	
	public Customer getCurrentCustomer(String key) {
		Optional<CurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		Integer customerId = optional.get().getCustomerId();
		
		return  customerDAO.getById(customerId);
	}
	
	public Wallet getCurrentUserWallet(String key) {
		Optional<CurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		Integer customerId = optional.get().getCustomerId();
		Customer customer = customerDAO.getById(customerId);
		
		Wallet wallet = customer.getWallet();
		
		return wallet;
	}
	
	public Bank getCurrentUserBank(String key) {
		Optional<CurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		Integer customerId = optional.get().getCustomerId();
		Customer customer = customerDAO.getById(customerId);
		
		Bank bank = customer.getWallet().getBank();
		
		return bank;
	}
	
}
