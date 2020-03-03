package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customerproduct database table.
 * 
 */
@Entity
@NamedQuery(name="Customerproduct.findAll", query="SELECT c FROM Customerproduct c")
public class Customerproduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerProductId;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;

	public Customerproduct() {
	}

	public int getCustomerProductId() {
		return this.customerProductId;
	}

	public void setCustomerProductId(int customerProductId) {
		this.customerProductId = customerProductId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return this.getProduct().getProductName();
	}
}