package com.infy.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.infy.dto.CustomerDTO;

class CustomerRowMapper implements RowMapper<CustomerDTO> {
	public CustomerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(rs.getInt("customer_id"));
		customerDTO.setEmailId(rs.getString("emailid"));
		customerDTO.setName(rs.getString("name"));
		customerDTO.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
		return customerDTO;
	}
}