package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the emp_photograph database table.
 * 
 */
@Entity
@Table(name="emp_photograph")
@NamedQuery(name="EmpPhotograph.findAll", query="SELECT e FROM EmpPhotograph e")
public class EmpPhotograph implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int photographId;

	private Timestamp dateCreated;

	private String photoName;

	@Lob
	private byte[] picture;

	//bi-directional one-to-one association to Employee
	@OneToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	public EmpPhotograph() {
	}

	public int getPhotographId() {
		return this.photographId;
	}

	public void setPhotographId(int photographId) {
		this.photographId = photographId;
	}

	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}