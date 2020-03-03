package com.project.servicedesk.business;

import com.project.servicedesk.entity.*;
import com.project.servicedesk.service.AppointmentServiceBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.*;

@Named(value = "appointmentBusinessBean")
@RequestScoped
public class AppointmentBusinessBean {

	private List<Appointment> appointmentList = new ArrayList<>();
	
	@EJB
	AppointmentServiceBean appointmentServiceBean;

	@PostConstruct
	public void init() {
		try {
			appointmentServiceBean.findAllAppointments();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public List<Appointment> getAppointmentList() {
		this.appointmentList = appointmentServiceBean.findAllAppointments();
		return this.appointmentList;
	}
}
