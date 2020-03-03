package com.project.servicedesk.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.project.servicedesk.entity.*;
import com.project.servicedesk.interfaces.EmployeeServiceBeanLocal;

@Stateless
@LocalBean
public class EmployeeServiceBean implements EmployeeServiceBeanLocal {
	
	@PersistenceContext(name="ServiceDesk")
	private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	@Override
	public void createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editEmployee(Employee employee) {
		em.merge(employee);
		
	}

	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

}
