package com.project.servicedesk.service;

import javax.persistence.*;

import com.project.servicedesk.entity.Fault;
import com.project.servicedesk.entity.Faulttype;

import javax.ejb.*;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class FaultServiceBean {
	
	@PersistenceContext(name="ServiceDesk")
	EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public List<Fault> findAll(){
		return em.createNamedQuery("Fault.findAll", Fault.class).getResultList();
	}
	
	public void editFault(Fault fault) {
		em.merge(fault);
	}
	
	public Faulttype findFaultType(String faultTypeName) throws Exception{
		Faulttype f = (Faulttype) em.createNamedQuery("Faulttype.findByTypeName",Faulttype.class)
				.setParameter("faultTypeName",faultTypeName).getSingleResult();
		return f;
	}
	
	public List<String> findAllFaultTypeNames(){
		String strQuery = "SELECT f.faultName FROM Faulttype f";
		return em.createQuery(strQuery, String.class).getResultList();
	}
	
	public List<Faulttype> findAllFaultTypes(){
		return em.createNamedQuery("Faulttype.findAll", Faulttype.class).getResultList();
	}
}
