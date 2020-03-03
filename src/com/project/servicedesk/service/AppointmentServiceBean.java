package com.project.servicedesk.service;

import com.project.servicedesk.entity.Appointment;
import com.project.servicedesk.entity.Appointmentstatus;
import com.project.servicedesk.entity.Appointmenttype;
import com.project.servicedesk.interfaces.AppointmentServiceBeanLocal;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class AppointmentServiceBean
 */
@Stateless
@LocalBean
public class AppointmentServiceBean implements AppointmentServiceBeanLocal {
	
	@PersistenceContext(name="ServiceDesk")
	private EntityManager em;
	
	protected EntityManager getEntityManager() {
		return em;
	}

    public AppointmentServiceBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createAppointment(Appointment appointment) {
		em.persist(appointment);
		
	}

	@Override
	public void editAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Appointment> findAllAppointments() {
		return em.createNamedQuery("Appointment.findAll", Appointment.class).getResultList();
	}
	
	
	public List<String> getAppointmentStatusNames(){
		return em.createNamedQuery("Appointmentstatus.findAllStatusNames", String.class).getResultList();
	}
	
	public Appointmentstatus findStatusByName(String appointmentStatusName) {
		String query = "SELECT DISTINCT a FROM Appointmentstatus a WHERE a.appointmentStatusName = '" + appointmentStatusName + "'";
		Appointmentstatus exisitingType = em.createQuery(query, Appointmentstatus.class).getSingleResult();
		return exisitingType;
	}

	public List<Appointmenttype> findAppointmentTypes(){
		return em.createNamedQuery("Appointmenttype.findAll", Appointmenttype.class).getResultList();
	}
	
	public List<String> getAppointmentTypeNames(){
		return em.createNamedQuery("Appointmenttype.findTypes", String.class).getResultList();
	}
	
	public Appointmenttype findAppointmentTypeByName(String appointmentTypeName) {
		String query = "SELECT DISTINCT a FROM Appointmenttype a WHERE a.appointmentTypeName = '" + appointmentTypeName + "'";
		Appointmenttype exisitingType = em.createQuery(query, Appointmenttype.class).getSingleResult();
		return exisitingType;
	}

}
