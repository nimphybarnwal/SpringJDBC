package com.infy.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.infy.dto.CustomerDTO;

@Repository(value = "customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	  This method will add details of received customer to the database.
	 
	  @param  customer object contains details of customer 
	  @return - void 
	 */
	public void addCustomer(CustomerDTO customer) {
		String query = "insert into customer (customer_id, emailid, name, date_of_birth) values (?,?,?,?)";

		jdbcTemplate.update(query, customer.getCustomerId(), customer.getEmailId(), customer.getName(),Date.valueOf(customer.getDateOfBirth()));
		

	}
	/**
	  This method will get details of customer from the database based on customerId received as parameter 
	  and handles EmptyResultDataAccessException when query returns empty result set.
	 
	  @param  -Integer customerId  
	  @return - Customer object fetched from query
	  
	 */
	public CustomerDTO getCustomer(Integer customerId) {
		String query = "select * from customer where customer_id = ?";
		try {
			return jdbcTemplate.queryForObject(query, new Object[] { customerId }, new CustomerRowMapper());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	/**
	  This method will get details of all the customer present in the database. 
	
	  @return - List of Customer object fetched from query  
	 */

	public List<CustomerDTO> getAllCustomers() {
		String query = "select * from customer";
		return jdbcTemplate.query(query, new CustomerRowMapper());
	}
	/**
	  This method will get Name of customer from the database based on customerId received as parameter 
	  and handles EmptyResultDataAccessException when query returns empty result set.
	 
	  @param  -Integer customerId  
	  @return - String customerName fetched from query
	  
	 */
	public String getCustomerName(Integer customerId) {
		String query = "select name from customer where customer_id = ?";
		try {
			return jdbcTemplate.queryForObject(query, new Object[] { customerId }, String.class);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	/**
	  This method will delete details of customer from the database based on customerId received as parameter. 
	  
	  @param  -Integer customerId  
	  @return - void
	  */

	public void deleteCustomer(Integer customerId) {
		String query = "delete from customer where customer_id = ?";
		jdbcTemplate.update(query, customerId);
	}
	/**
	  This method will update emailId  of the customer present in  the database based on customerId received in parameter 
	  with the new emailId received in parameter.
	 
	  @param  -Integer customerId  String emailId
	  @return - void
	  */
	public void updateCustomer(Integer customerId, String emailId) {
		String query = "update customer set emailid = ? where customer_id = ?";
		jdbcTemplate.update(query, emailId, customerId);
	}
	
	

	

}
