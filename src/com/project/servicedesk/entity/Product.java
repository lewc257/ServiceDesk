package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQueries({@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p"),
	@NamedQuery(name="Product.findByName", query="SELECT DISTINCT p FROM Product p WHERE p.productName= :productName"),
	@NamedQuery(name="Product.findByType",query="SELECT p FROM Product p WHERE p.producttype.productType =:productType")
})
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productId;

	private String productName;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="product")
	private List<Appointment> appointments;

	//bi-directional many-to-one association to Customerproduct
	@OneToMany(mappedBy="product")
	private List<Customerproduct> customerproducts;

	//bi-directional many-to-one association to Fault
	@OneToMany(mappedBy="product")
	private List<Fault> faults;

	//bi-directional many-to-one association to Producttype
	@ManyToOne
	@JoinColumn(name="productTypeId")
	private Producttype producttype;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		if (getAppointments().isEmpty()) {
			this.appointments = new ArrayList<Appointment>();
		}
		
		getAppointments().add(appointment);
		appointment.setProduct(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setProduct(null);

		return appointment;
	}

	public List<Customerproduct> getCustomerproducts() {
		return this.customerproducts;
	}

	public void setCustomerproducts(List<Customerproduct> customerproducts) {
		this.customerproducts = customerproducts;
	}

	public Customerproduct addCustomerproduct(Customerproduct customerproduct) {
		getCustomerproducts().add(customerproduct);
		customerproduct.setProduct(this);

		return customerproduct;
	}

	public Customerproduct removeCustomerproduct(Customerproduct customerproduct) {
		getCustomerproducts().remove(customerproduct);
		customerproduct.setProduct(null);

		return customerproduct;
	}

	public List<Fault> getFaults() {
		return this.faults;
	}

	public void setFaults(List<Fault> faults) {
		this.faults = faults;
	}

	public Fault addFault(Fault fault) {
		getFaults().add(fault);
		fault.setProduct(this);

		return fault;
	}

	public Fault removeFault(Fault fault) {
		getFaults().remove(fault);
		fault.setProduct(null);

		return fault;
	}

	public Producttype getProducttype() {
		return this.producttype;
	}

	public void setProducttype(Producttype producttype) {
		this.producttype = producttype;
	}

}