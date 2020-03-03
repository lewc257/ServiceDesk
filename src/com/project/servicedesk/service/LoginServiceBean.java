package com.project.servicedesk.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.servicedesk.entity.Useraccount;
import com.project.servicedesk.entity.Customer;
import com.project.servicedesk.entity.Faulttype;
import com.project.servicedesk.entity.Product;

@Stateless
@LocalBean
public class LoginServiceBean {
	@PersistenceContext(name = "ServiceDesk")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}
	
	public void createUseraccount(Useraccount userAccount) {
		em.persist(userAccount);
	}
	
	public void editUseraccount(Useraccount userAccount) {
		em.merge(userAccount);
	}

	public Useraccount findUseraccount(String username) throws Exception {
		Useraccount u = (Useraccount) em.createNamedQuery("Useraccount.findByUsername", Useraccount.class)
				.setParameter("userName", username).getSingleResult();
		return u;
	}

	public Useraccount find(String userName, String userPassword) throws Exception {
		Useraccount u = (Useraccount) em.createNamedQuery("Useraccount.find", Useraccount.class)
				.setParameter("userName", userName).setParameter("userPassword", userPassword).getSingleResult();
		return u;
	}

	public Customer findCustomer(Useraccount useraccount) throws Exception {
		Customer c = (Customer) em.createNamedQuery("Customer.findByUsername", Customer.class)
				.setParameter("useraccount", useraccount).getSingleResult();
		return c;
	}
	
	public Product findProduct(String productName) throws Exception{
		Product p = (Product) em.createNamedQuery("Product.findByName",Product.class)
				.setParameter("productName",productName).getSingleResult();
		return p;
	}
	
	public Faulttype findFaultType(String faultTypeName) throws Exception{
		Faulttype f = (Faulttype) em.createNamedQuery("Faulttype.findByTypeName",Faulttype.class)
				.setParameter("faultTypeName",faultTypeName).getSingleResult();
		return f;
	}

}