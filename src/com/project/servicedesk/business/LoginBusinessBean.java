package com.project.servicedesk.business;

import com.project.servicedesk.entity.*;
import com.project.servicedesk.service.LoginServiceBean;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import javax.inject.Named;

@Named(value = "loginBusinessBean")
@ApplicationScoped
public class LoginBusinessBean{

	Useraccount useraccount;
	Customer loggedInCustomer;
	Employee loggedInEmployee;
	String username;
	String password;

	@EJB
	LoginServiceBean loginServiceBean;

	public LoginBusinessBean() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getLoggedInCustomer() {
		return loggedInCustomer;
	}

	public Employee getLoggedInEmployee() {
		return loggedInEmployee;
	}

	public Useraccount getLoggedInUser() {
		return useraccount;
	}

	public String login() {
		try {
			useraccount = loginServiceBean.findUseraccount(username);
			if (useraccount.getUserPassword().equals(password)) {
				if (useraccount.getEmployee() != null) {
					loggedInEmployee = useraccount.getEmployee();
					return "employee_dashboard";
				} else if (useraccount.getCustomer() != null) {
					loggedInCustomer = useraccount.getCustomer();
					return "customer_dashboard";
				} else {
					throw new Exception("User neither has customer or employee");
				}
			} else {
				return "failure"; // TODO: Error message in JSF if username or password is incorrect
			}
		} catch (Exception e) {
			return "failure";
		}
	}
}