package com.project.servicedesk.business;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.project.servicedesk.entity.*;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import com.project.servicedesk.service.CustomerServiceBean;
import com.project.servicedesk.service.FaultServiceBean;
import com.project.servicedesk.service.ProductServiceBean;

@Named(value = "customerBusinessBean")
@SessionScoped
public class CustomerBusinessBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	CustomerServiceBean customerServiceBean;
	
	@EJB
	ProductServiceBean productServiceBean;
	
	@EJB
	FaultServiceBean faultServiceBean;
	
	DetailEntry entry;
	Customer selectedCustomer;
	String selectedProductName;
	String selectedProductTypeName;
	String selectedFaultTypeName;
	String faultComments;
	
	public String getFaultComments() {
		return faultComments;
	}

	public void setFaultComments(String faultComments) {
		this.faultComments = faultComments;
	}

	public String getSelectedFaultTypeName() {
		return selectedFaultTypeName;
	}

	public void setSelectedFaultTypeName(String selectedFaultTypeName) {
		this.selectedFaultTypeName = selectedFaultTypeName;
	}

	
	public String getSelectedProductName() {
		return selectedProductName;
	}

	public void setSelectedProductName(String selectedProductName) {
		this.selectedProductName = selectedProductName;
	}
	
	public String getSelectedProductTypeName() {
		return selectedProductTypeName;
	}

	public void setSelectedProductTypeName(String selectedProductTypeName) {
		this.selectedProductTypeName = selectedProductTypeName;
	}
	
	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
	
	public DetailEntry getDetailEntry() {
		return entry;
	}
	
	public String gotoCustomerProductPage(Customer customer) {
		selectedCustomer = customer;
		return "customer_products";
	}

	public String customerEditForm(Customer customer) {
		selectedCustomer = customer;
		entry = new DetailEntry();
		entry.setUsername(customer.getUseraccount().getUserName());
		entry.setPassword(customer.getUseraccount().getUserPassword());
		entry.setTitle(customer.getTitle());
		entry.setFirstName(customer.getFirstName());
		entry.setMiddleName(customer.getMiddleName());
		entry.setLastName(customer.getLastName());
		entry.setContactNumber(customer.getCusDetail().getContactPhone());
		entry.setPersonalEmail(customer.getCusDetail().getPersonalEmail());
		entry.setAddressLine1(customer.getCusAddresses().get(0).getAddress1());
		entry.setAddressLine2(customer.getCusAddresses().get(0).getAddress2());
		entry.setStreet(customer.getCusAddresses().get(0).getStreet());
		entry.setCity(customer.getCusAddresses().get(0).getCity());
		entry.setPostcode(customer.getCusAddresses().get(0).getPostcode());
		entry.setCountry(customer.getCusAddresses().get(0).getCountry());

		return "customer_edit";
	}

	public String editCustomer() {
		selectedCustomer.getUseraccount().setUserPassword(entry.getPassword());
		selectedCustomer.setTitle(entry.getTitle());
		selectedCustomer.setFirstName(entry.getFirstName());
		selectedCustomer.setMiddleName(entry.getMiddleName());
		selectedCustomer.setLastName(entry.getLastName());
		selectedCustomer.getCusDetail().setContactPhone(entry.getContactNumber());
		selectedCustomer.getCusDetail().setPersonalEmail(entry.getPersonalEmail());
		selectedCustomer.getCusAddresses().get(0).setAddress1(entry.getAddressLine1());
		selectedCustomer.getCusAddresses().get(0).setAddress2(entry.getAddressLine2());
		selectedCustomer.getCusAddresses().get(0).setStreet(entry.getStreet());
		selectedCustomer.getCusAddresses().get(0).setCity(entry.getCity());
		selectedCustomer.getCusAddresses().get(0).setPostcode(entry.getPostcode());
		selectedCustomer.getCusAddresses().get(0).setCountry(entry.getCountry());

		customerServiceBean.editCustomer(selectedCustomer);

		return "customer_dashboard";
	}
	
	public String removeCustomerProduct(Customerproduct customerproduct) {
		customerproduct.setCustomer(null);
		customerproduct.setProduct(null);
		customerServiceBean.deleteCustomerProduct(customerproduct);
		selectedCustomer.removeCustomerproduct(customerproduct);
		return "customer_products";
	}
	
	public String addCustomerProduct() {
		Customerproduct customerProduct = new Customerproduct();
		try {
			Product selectedProduct = productServiceBean.findProductByName(selectedProductName);
			customerProduct.setCustomer(selectedCustomer);
			customerProduct.setProduct(selectedProduct);
			customerServiceBean.createCustomerProduct(customerProduct);
			selectedCustomer.addCustomerproduct(customerProduct);
			return "customer_products";
		} catch (Exception e) {
			return "failure";
		}
	}
	 
	public String reportFault(Customer customer) {
		Fault fault = new Fault();
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		try {
			Product product = productServiceBean.findProductByName(selectedProductName);
			Faulttype faulttype = faultServiceBean.findFaultType(selectedFaultTypeName);
			fault.setProduct(product);
			fault.setFaulttype(faulttype);
			fault.setDateReported(timestamp);
			fault.setComments(faultComments);
			customer.addFault(fault);
			customerServiceBean.editCustomer(customer);
			return "customer_dashboard";
		}
		catch (Exception e) {
			return "failure";
		}

	}
}

