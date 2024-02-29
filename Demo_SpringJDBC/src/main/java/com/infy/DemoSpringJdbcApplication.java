package com.infy;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.dto.CustomerDTO;
import com.infy.service.CustomerService;

@SpringBootApplication
public class DemoSpringJdbcApplication implements CommandLineRunner {
	
	public static final Log LOGGER = LogFactory.getLog(DemoSpringJdbcApplication.class);

	@Autowired
	CustomerService customerService;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringJdbcApplication.class, args);
	}

	public void run(String... args) throws Exception {
		addCustomer();
//		getCustomer();
//		getCustomerName();
//		getAllCustomers();
//		updateCustomer();
//		deleteCustomer();
	}
	
	public void addCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(2);
		customerDTO.setEmailId("tim@infy.com");
		customerDTO.setName("Tim Cook");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 5, 12));

		try {
			customerService.addCustomer(customerDTO);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}

	}

	public void getCustomer() {
		try {

			CustomerDTO customerDTO = customerService.getCustomer(2);

			LOGGER.info(customerDTO);

		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void getCustomerName() {
		try {

			String name = customerService.getCustomerName(2);
			LOGGER.info("Name : " + name);
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}
	
	public void getAllCustomers() {
		try {
			List<CustomerDTO> customerDTOs = customerService.getAllCustomers();
			for (CustomerDTO customerDTO : customerDTOs) {
				LOGGER.info(customerDTO);
			}
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void updateCustomer() {
		try {
			customerService.updateCustomer(2, "tim01@infy.com");
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void deleteCustomer() {
		try {
			customerService.deleteCustomer(2);
			LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}


}
