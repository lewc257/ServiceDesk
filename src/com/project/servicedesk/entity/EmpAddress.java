package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the emp_address database table.
 * 
 */
@Entity
@Table(name="emp_address")
@NamedQuery(name="EmpAddress.findAll", query="SELECT e FROM EmpAddress e")
public class EmpAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;

	private String address1;

	private String address2;

	private String city;

	private String country;

	private String postcode;

	private String street;

	//bi-directional one-to-one association to Employee
	@OneToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	public EmpAddress() {
	}

	public int getAddressId() {
		return this.addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}