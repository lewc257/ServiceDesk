package com.project.servicedesk.interfaces;

import java.util.List;

import com.project.servicedesk.entity.*;

public interface CustomerServiceBeanLocal {
	public void createCustomer(Customer customer);
	public void editCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
	public List<Customer> findAllCustomers();
}
