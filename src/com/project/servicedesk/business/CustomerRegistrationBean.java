package com.project.servicedesk.business;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.project.servicedesk.entity.CusAddress;
import com.project.servicedesk.entity.CusDetail;
import com.project.servicedesk.entity.Customer;
import com.project.servicedesk.entity.Useraccount;
import com.project.servicedesk.service.CustomerServiceBean;

@Named("customerRegistrationBean")
@RequestScoped
public class CustomerRegistrationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	CustomerServiceBean customerServiceBean;
	
	DetailEntry entry = new DetailEntry();
	
	public DetailEntry getDetailEntry() {
		return entry;
	}
	
	public void setDetailEntry(DetailEntry entry) {
		this.entry = entry;
	}
	
	public String addCustomer() {
		Customer customer = new Customer();

		Useraccount newAccount = new Useraccount();
		newAccount.setUserName(entry.getPersonalEmail());
		newAccount.setUserPassword(entry.getPassword());

		customer.setLastName(entry.getLastName());
		customer.setTitle(entry.getTitle());
		customer.setFirstName(entry.getFirstName());
		customer.setMiddleName(entry.getMiddleName());
		customer.setUseraccount(newAccount);

		CusDetail detail = new CusDetail();
		detail.setContactPhone(entry.getContactNumber());
		detail.setPersonalEmail(entry.getPersonalEmail());
		customer.setCusDetail(detail);

		CusAddress address = new CusAddress();
		address.setAddress1(entry.getAddressLine1());
		address.setAddress2(entry.getAddressLine2());
		address.setStreet(entry.getStreet());
		address.setCity(entry.getCity());
		address.setPostcode(entry.getPostcode());
		address.setCountry(entry.getCountry());
		customer.addCusAddress(address);

		customerServiceBean.createCustomer(customer);
		
		return "login";
	}
}
