package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the emp_detail database table.
 * 
 */
@Entity
@Table(name="emp_detail")
@NamedQuery(name="EmpDetail.findAll", query="SELECT e FROM EmpDetail e")
public class EmpDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int detailId;

	private String personalEmail;

	private String workEmail;

	//bi-directional one-to-one association to Employee
	@OneToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	public EmpDetail() {
	}

	public int getDetailId() {
		return this.detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public String getPersonalEmail() {
		return this.personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getWorkEmail() {
		return this.workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}