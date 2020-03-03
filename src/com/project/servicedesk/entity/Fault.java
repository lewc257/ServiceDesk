package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the fault database table.
 * 
 */
@Entity
@NamedQuery(name="Fault.findAll", query="SELECT f FROM Fault f")
public class Fault implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int faultId;

	private String comments;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAccepted;

	private Date dateReported;

	//bi-directional one-to-one association to Appointment
	@OneToOne(mappedBy="fault")
	private Appointment appointment;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;

	//bi-directional many-to-one association to Faulttype
	@ManyToOne
	@JoinColumn(name="faultTypeId")
	private Faulttype faulttype;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;
 
	public Fault() {
	}

	public int getFaultId() {
		return this.faultId;
	}

	public void setFaultId(int faultId) {
		this.faultId = faultId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDateAccepted() {
		return this.dateAccepted;
	}

	public void setDateAccepted(Date dateAccepted) {
		this.dateAccepted = dateAccepted;
	}

	public Date getDateReported() {
		return this.dateReported;
	}

	public void setDateReported(Date dateReported) {
		this.dateReported = dateReported;
	}

	public Appointment getAppointment() {
		return this.appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Faulttype getFaulttype() {
		return this.faulttype;
	}

	public void setFaulttype(Faulttype faulttype) {
		this.faulttype = faulttype;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}