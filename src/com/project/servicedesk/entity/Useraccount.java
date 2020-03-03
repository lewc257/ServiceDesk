package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the useraccount database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Useraccount.findAll", query="SELECT u FROM Useraccount u"),
	@NamedQuery(name="Useraccount.findByUsername", query="SELECT DISTINCT u FROM Useraccount u WHERE u.userName = :userName"),
	@NamedQuery(name="Useraccount.find", query="SELECT DISTINCT u FROM Useraccount u WHERE u.userName = :userName AND u.userPassword = :userPassword")
})

public class Useraccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String userName;

	private String message;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;

	private String userPassword;

	//bi-directional one-to-one association to Customer
	@OneToOne(mappedBy="useraccount")
	private Customer customer;

	//bi-directional one-to-one association to Employee
	@OneToOne(mappedBy="useraccount")
	private Employee employee;

	public Useraccount() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}