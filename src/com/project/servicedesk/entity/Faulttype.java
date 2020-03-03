package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the faulttype database table.
 * 
 */
@Entity
@NamedQueries({@NamedQuery(name="Faulttype.findAll", query="SELECT f FROM Faulttype f"),
	@NamedQuery(name="Faulttype.findByTypeName", query="SELECT DISTINCT f FROM Faulttype f WHERE f.faultName=:faultTypeName")
})
public class Faulttype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int faultTypeId;

	private String faultName;

	//bi-directional many-to-one association to Fault
	@OneToMany(mappedBy="faulttype")
	private List<Fault> faults;

	public Faulttype() {
	}

	public int getFaultTypeId() {
		return this.faultTypeId;
	}

	public void setFaultTypeId(int faultTypeId) {
		this.faultTypeId = faultTypeId;
	}

	public String getFaultName() {
		return this.faultName;
	}

	public void setFaultName(String faultName) {
		this.faultName = faultName;
	}

	public List<Fault> getFaults() {
		return this.faults;
	}

	public void setFaults(List<Fault> faults) {
		this.faults = faults;
	}

	public Fault addFault(Fault fault) {
		getFaults().add(fault);
		fault.setFaulttype(this);

		return fault;
	}

	public Fault removeFault(Fault fault) {
		getFaults().remove(fault);
		fault.setFaulttype(null);

		return fault;
	}

}