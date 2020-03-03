package com.project.servicedesk.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the appointment database table.
 * 
 */
@Entity
@NamedQuery(name="Appointment.findAll", query="SELECT a FROM Appointment a")
public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int appointmentId;

	private Timestamp appointmentDate;

	//bi-directional many-to-one association to Appointmentstatus
	@ManyToOne
	@JoinColumn(name="appointmentStatusId")
	private Appointmentstatus appointmentstatus;

	//bi-directional many-to-one association to Appointmenttype
	@ManyToOne
	@JoinColumn(name="appointmentTypeId")
	private Appointmenttype appointmenttype;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;

	//bi-directional one-to-one association to Fault
	@OneToOne
	@JoinColumn(name="faultId")
	private Fault fault;

	public Appointment() {
	}

	public int getAppointmentId() {
		return this.appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Timestamp getAppointmentDate() {
		return this.appointmentDate;
	}

	public void setAppointmentDate(Timestamp appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Appointmentstatus getAppointmentstatus() {
		return this.appointmentstatus;
	}

	public void setAppointmentstatus(Appointmentstatus appointmentstatus) {
		this.appointmentstatus = appointmentstatus;
	}

	public Appointmenttype getAppointmenttype() {
		return this.appointmenttype;
	}

	public void setAppointmenttype(Appointmenttype appointmenttype) {
		this.appointmenttype = appointmenttype;
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

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Fault getFault() {
		return this.fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}

}