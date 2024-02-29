package com.infy.repository;

import java.util.List;

import com.infy.dto.CustomerDTO;

public interface CustomerRepository {
	public void addCustomer(CustomerDTO customer);
	public CustomerDTO getCustomer(Integer customerId);
	public String getCustomerName(Integer customerId);
	public List<CustomerDTO> getAllCustomers();
	public void updateCustomer(Integer customerId, String emailId);
	public void deleteCustomer(Integer customerId);
	
}
