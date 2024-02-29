package com.infy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	

	/**
	 * This method first calls getCustomer method of CustomerDAOImpl by passing
	 * customerId from customerDTO object received in parameter to check whether
	 * customerDTO already exist in database or not.If customerDTO object received in
	 * parameter are not present in database then calls addCustomer method of
	 * CustomerDAOImpl to add customerDTO details to the database
	 * 
	 * @param -Customer
	 *            object
	 * 
	 * @throws -
	 *             Service.CUSTOMER_CANNOT_BE_ADDED exception if Customer object
	 *             returned from getCustomer method of CustomerDAOImpl is not
	 *             null
	 */

	public void addCustomer(CustomerDTO customerDTO) throws InfyBankException {

		if (customerRepository.getCustomer(customerDTO.getCustomerId()) == null) {
			customerRepository.addCustomer(customerDTO);
		} else {
			throw new InfyBankException("Service.CUSTOMER_CANNOT_BE_ADDED");
		}

	}

	/**
	 * This method calls getCustomer method of CustomerDAOImpl by passing
	 * customerId received in parameter to get corresponding customerDTO details
	 * 
	 * @param -Integer 
	 *            customerId
	 * @return - Customer object returned from getCustomer method of
	 *         CustomerDAOImpl
	 * @throws -
	 *             Service.CUSTOMER_NOT_FOUND exception if Customer object
	 *             returned from getCustomer method of CustomerDAOImpl is null
	 */
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {

		CustomerDTO customerDTO = customerRepository.getCustomer(customerId);

		if (customerDTO == null) {

			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");

		}

		return customerDTO;

	}
	/**
	 * This method calls getCustomerName method of CustomerDAOImpl to get Name
	 * of the customerDTO from the database by passing customerId received in
	 * parameter
	 * 
	 * @param Integer
	 *            customerId
	 * @return String name returned from getCustomerName method of
	 *         CustomerDAOImpl
	 * 
	 * @throws -
	 *             Service.CUSTOMER_NOT_FOUND exception if return from
	 *             getCustomerName of CustomerDAOImpl is null
	 */
	public String getCustomerName(Integer customerId) throws InfyBankException {
		String name = customerRepository.getCustomerName(customerId);
		if (name == null) {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}

		return name;

	}


	/**
	 * This method calls getAllCustomers method of CustomerDAOImpl to get
	 * details of all customerDTO present in the database
	 * 
	 * @return List<Customer> details returned from getAllCustomers method of
	 *         CustomerDAOImpl
	 * 
	 * @throws -
	 *             Service.NO_CUSTOMER_AVAIALBLE exception if return from
	 *             getAllCustomers of CustomerDAOImpl is empty list
	 */

	public List<CustomerDTO> getAllCustomers() throws InfyBankException {
		List<CustomerDTO> customers = customerRepository.getAllCustomers();
		if (customers.size() == 0) {
			throw new InfyBankException("Service.NO_CUSTOMER_AVAIALBLE");
		} else
			return customers;
	}
	
	/**
	 * This method first calls getCustomer method of CustomerDAOImpl by passing
	 * customerId received in parameter to check whether customerDTO exist in
	 * database or not.If return from getCustomer method of CustomerDAOImpl is
	 * not null then calls updateCustomer method of CustomerDAOImpl to update
	 * existing customerDTO emailId
	 * 
	 * 
	 * @param -Integer
	 *            customerId, String latest emailId
	 * 
	 * @throws -
	 *             Service.CUSTOMER_NOT_FOUND exception if return from
	 *             getCustomer of CustomerDAOImpl is null
	 */
	public void updateCustomer(Integer customerId, String emailId) throws InfyBankException {

		if (customerRepository.getCustomer(customerId) != null) {
			customerRepository.updateCustomer(customerId, emailId);
		} else {
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
		}

	}

	/**
	 * This method first calls getCustomer method of CustomerDAOImpl by passing
	 * customerId received in parameter to check whether customerDTO exist in
	 * database or not.If return from getCustomer method of CustomerDAOImpl is
	 * not null then calls deleteCustomer method of CustomerDAOImpl to remove
	 * customerDTO details from the database
	 * 
	 * 
	 * @param -Interger
	 *            customerId
	 * 
	 * @throws -
	 *             Service.CUSTOMER_NOT_FOUND exception if return from
	 *             getCustomer of CustomerDAOImpl is null
	 */
	public void deleteCustomer(Integer customerId) throws InfyBankException {

		if (customerRepository.getCustomer(customerId) != null) {
			customerRepository.deleteCustomer(customerId);
		} else
			throw new InfyBankException("Service.CUSTOMER_NOT_FOUND");
	}

}
