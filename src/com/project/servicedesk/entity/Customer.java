package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;



/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c"),
	@NamedQuery(name="Customer.find", query="SELECT DISTINCT c FROM Customer c WHERE c = :customer")
})
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerId;

	private Timestamp created;

	private String firstName;

	private String lastName;

	private String middleName;

	private String title;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	private List<Appointment> appointments;

	//bi-directional many-to-one association to CusAddress
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	private List<CusAddress> cusAddresses;

	//bi-directional one-to-one association to CusDetail
	@OneToOne(mappedBy="customer", cascade=CascadeType.ALL)
	private CusDetail cusDetail;

	//bi-directional one-to-one association to Useraccount
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userName")
	private Useraccount useraccount;

	//bi-directional many-to-one association to Customerproduct
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	private List<Customerproduct> customerproducts;

	//bi-directional many-to-one association to Fault
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	private List<Fault> faults;

	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFullName() {
		String middleName = this.middleName == null? "" : this.middleName;
		return this.firstName + " " + middleName + " " + this.lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setCustomer(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setCustomer(null);

		return appointment;
	}

	public List<CusAddress> getCusAddresses() {
		return this.cusAddresses;
	}

	public void setCusAddresses(List<CusAddress> cusAddresses) {
		this.cusAddresses = cusAddresses;
	}

	public CusAddress addCusAddress(CusAddress cusAddress) {
		if (getCusAddresses() == null) {
			this.cusAddresses = new ArrayList<>();
		}
		getCusAddresses().add(cusAddress);
		cusAddress.setCustomer(this);

		return cusAddress;
	}

	public CusAddress removeCusAddress(CusAddress cusAddress) {
		if (getCusAddresses() == null) {
			this.cusAddresses = new ArrayList<>();
		}
		
		if (!getCusAddresses().isEmpty()) {
			getCusAddresses().remove(cusAddress);
			cusAddress.setCustomer(null);
		}
		return cusAddress;
	}

	public CusDetail getCusDetail() {
		return this.cusDetail;
	}

	public void setCusDetail(CusDetail cusDetail) {
		this.cusDetail = cusDetail;
		cusDetail.setCustomer(this);
	}

	public Useraccount getUseraccount() {
		return this.useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
		useraccount.setCustomer(this);
	}

	public List<Customerproduct> getCustomerproducts() {
		return this.customerproducts;
	}
	
	public boolean customerAppointmentsIsEmpty() {
		return this.getAppointments().size() == 0;
	}

	public boolean customerAppointmentsIsNotEmpty() {
		return this.getAppointments().size() > 0;
	}
	
	public boolean customerProductsIsEmpty() {
		return this.getCustomerproducts().size() == 0;
	}

	public boolean customerProductsIsNotEmpty() {
		return this.getCustomerproducts().size() > 0;
	}

	public void setCustomerproducts(List<Customerproduct> customerproducts) {
		this.customerproducts = customerproducts;
	}

	public Customerproduct addCustomerproduct(Customerproduct customerproduct) {
		if (getCustomerproducts() == null) {
			this.customerproducts = new ArrayList<>();
		}
		getCustomerproducts().add(customerproduct);
		customerproduct.setCustomer(this);

		return customerproduct;
	}

	public Customerproduct removeCustomerproduct(Customerproduct customerproduct) {
		if (getCustomerproducts() == null) {
			this.customerproducts = new ArrayList<>();
		}
		
		if (!getCustomerproducts().isEmpty()) {
			getCustomerproducts().remove(customerproduct);
			customerproduct.setCustomer(null);
		}

		return customerproduct;
	}

	public List<Fault> getFaults() {
		return this.faults;
	}

	public void setFaults(List<Fault> faults) {
		this.faults = faults;
	}

	public Fault addFault(Fault fault) {
		if (getFaults() == null) {
			this.faults = new ArrayList<>();
		}
		getFaults().add(fault);
		fault.setCustomer(this);

		return fault;
	}

	public Fault removeFault(Fault fault) {
		if (getFaults() == null) {
			this.faults = new ArrayList<>();
		}
		
		if (!getFaults().isEmpty()) {
			getFaults().remove(fault);
			fault.setCustomer(null);
		}

		return fault;
	}

}