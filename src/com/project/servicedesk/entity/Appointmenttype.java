package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the appointmenttype database table.
 * 
 */
@Entity

@NamedQueries({
	@NamedQuery(name="Appointmenttype.findAll", query="SELECT a FROM Appointmenttype a"),
	@NamedQuery(name="Appointmenttype.findTypes", query="SELECT a.appointmentTypeName FROM Appointmenttype a")
})
public class Appointmenttype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int appointmentTypeId;

	private String appointmentTypeName;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="appointmenttype")
	private List<Appointment> appointments;

	public Appointmenttype() {
	}

	public int getAppointmentTypeId() {
		return this.appointmentTypeId;
	}

	public void setAppointmentTypeId(int appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	public String getAppointmentTypeName() {
		return this.appointmentTypeName;
	}

	public void setAppointmentTypeName(String appointmentTypeName) {
		this.appointmentTypeName = appointmentTypeName;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setAppointmenttype(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setAppointmenttype(null);

		return appointment;
	}
	
	@Override
	public String toString() {
		return getAppointmentTypeName();
	}
}