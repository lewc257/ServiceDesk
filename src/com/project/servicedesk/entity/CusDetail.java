package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cus_detail database table.
 * 
 */
@Entity
@Table(name="cus_detail")
@NamedQuery(name="CusDetail.findAll", query="SELECT c FROM CusDetail c")
public class CusDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int detailId;

	private String contactPhone;

	private String personalEmail;

	//bi-directional one-to-one association to Customer
	@OneToOne
	@JoinColumn(name="customerId")
	private Customer customer;

	public CusDetail() {
	}

	public int getDetailId() {
		return this.detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getPersonalEmail() {
		return this.personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}