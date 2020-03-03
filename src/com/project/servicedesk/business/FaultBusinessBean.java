package com.project.servicedesk.business;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import com.project.servicedesk.service.AppointmentServiceBean;
import com.project.servicedesk.service.FaultServiceBean;
import com.project.servicedesk.entity.Appointment;
import com.project.servicedesk.entity.Appointmentstatus;
import com.project.servicedesk.entity.Appointmenttype;
import com.project.servicedesk.entity.Employee;
import com.project.servicedesk.entity.Fault;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Named(value="faultBusinessBean")
@SessionScoped
public class FaultBusinessBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	FaultServiceBean faultServiceBean;
	
	@EJB
	AppointmentServiceBean appointmentServiceBean;
	
	String selectedAppointmentTypeName;
	String selectedAppointmentStatusName;
	String selectedDate;

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	Fault selectedFault;
	
	List<Fault> faultList = new ArrayList<Fault>();
	List<String> appointmentTypesNames = new ArrayList<String>();
	List<String> appointmentStatusNames = new ArrayList<String>();
	List<String> faultTypeNames = new ArrayList<String>();
	
	@PostConstruct
	public void init() {
		
	}
	
	public Fault getSelectedFault() {
		return selectedFault;
	}

	public void setSelectedFault(Fault selectedFault) {
		this.selectedFault = selectedFault;
	}
	
	public List<Fault> getFaults() {
		faultList = faultServiceBean.findAll()
				.stream().filter(f -> f.getAppointment() == null)
				.collect(Collectors.toList());
		return faultList;
	}
	
	public List<String> getFaultTypeNames(){
		this.faultTypeNames = faultServiceBean.findAllFaultTypeNames();
		return faultTypeNames;
	}
	
	//Appointment Types
	public List<String> getAppointmentTypeNames() {
		appointmentTypesNames = appointmentServiceBean.getAppointmentTypeNames();
		return appointmentTypesNames;
	}
	
	public String getSelectedAppointmentTypeName() {
		return selectedAppointmentTypeName;
	}
	
	public void setSelectedAppointmentTypeName(String selectedAppointmentTypeName) {
		this.selectedAppointmentTypeName = selectedAppointmentTypeName;
	}
	
	//Appointment Status
	public List<String> getAppointmentStatusNames() {
		appointmentStatusNames = appointmentServiceBean.getAppointmentStatusNames();
		return appointmentStatusNames;
	}
	
	public String getSelectedAppointmentStatusName() {
		return selectedAppointmentStatusName;
	}

	public void setSelectedAppointmentStatusName(String selectedAppointmentStatusName) {
		this.selectedAppointmentStatusName = selectedAppointmentStatusName;
	}
	
	//Event handlers
	public void onSelectAppointmentTypeName(ValueChangeEvent evt) {
		String newValue = (String) evt.getNewValue();
		if (newValue != null) {
			selectedAppointmentTypeName = newValue;
		}
	}
	
	public void onSelectedAppointmentStatusName(ValueChangeEvent evt) {
		String newValue = (String) evt.getNewValue();
		if (newValue != null) {
			selectedAppointmentStatusName = newValue;
		}
	}
	
	public String bookAppointmentAction(Fault fault) {
		this.selectedFault = fault;
		return "book_appointment";
	}
	
	public String bookAppointment(Employee loggedInEmployee) {
		if (selectedAppointmentTypeName == null || selectedDate == null 
				|| selectedAppointmentStatusName == null) {
			return "";
		}
		try {
			Appointment appointment = new Appointment();
			
			selectedFault.setDateAccepted(new Date());
			
			appointment.setFault(selectedFault);
			appointment.setCustomer(selectedFault.getCustomer());
			appointment.setProduct(selectedFault.getProduct());
			appointment.setEmployee(loggedInEmployee);
			
			Timestamp timestamp = Timestamp.valueOf(selectedDate);
			appointment.setAppointmentDate(timestamp);
			
			Appointmenttype appointmentType = appointmentServiceBean.findAppointmentTypeByName(selectedAppointmentTypeName);
			appointment.setAppointmenttype(appointmentType);
			
			Appointmentstatus appointmentStatus = appointmentServiceBean.findStatusByName(selectedAppointmentStatusName);
			appointment.setAppointmentstatus(appointmentStatus);
			
			selectedFault.setAppointment(appointment);

			faultServiceBean.editFault(selectedFault);
			loggedInEmployee.addAppointment(appointment);
			return "view_all_faults";
		} catch (Exception e) {
			return "failure";
		}
	}
}
