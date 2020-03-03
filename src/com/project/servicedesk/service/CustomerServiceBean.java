package com.project.servicedesk.service;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.PersistenceContext;

import com.project.servicedesk.business.DetailEntry;
import com.project.servicedesk.entity.CusAddress;
import com.project.servicedesk.entity.CusDetail;
import com.project.servicedesk.entity.Customer;
import com.project.servicedesk.entity.Customerproduct;
import com.project.servicedesk.entity.Useraccount;
import com.project.servicedesk.interfaces.*;

@Stateless
@LocalBean
public class CustomerServiceBean implements CustomerServiceBeanLocal{

	@PersistenceContext(name="ServiceDesk")
	private EntityManager em;

	
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public void createCustomer(Customer customer) {
		em.persist(customer);
	}

	@Override
	public void editCustomer(Customer customer) {
		em.merge(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		em.remove(customer);
	}
	
	public void deleteCustomerProduct(Customerproduct customerProduct) {
		if (!em.contains(customerProduct)) {
			customerProduct = em.merge(customerProduct);
		}
		em.remove(customerProduct);
	}
	
	public void createCustomerProduct(Customerproduct customerProduct) {
		em.persist(customerProduct);
	}

	@Override
	public List<Customer> findAllCustomers() {
		return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
	}
	
	public Customer find(Customer customer) {
		Customer c = (Customer) em.createNamedQuery("Customer.find", Customer.class)
				.setParameter("customer", customer).getSingleResult();
		return c;
	}
}
