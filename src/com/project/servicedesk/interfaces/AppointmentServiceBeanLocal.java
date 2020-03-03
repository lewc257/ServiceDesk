package com.project.servicedesk.interfaces;

import javax.ejb.Local;
import com.project.servicedesk.entity.*;
import java.util.*;

@Local
public interface AppointmentServiceBeanLocal {
	
	public void createAppointment(Appointment appointment);
	public void editAppointment(Appointment appointment);
	public void deleteAppointment(Appointment appointment);
	public List<Appointment> findAllAppointments();
}
