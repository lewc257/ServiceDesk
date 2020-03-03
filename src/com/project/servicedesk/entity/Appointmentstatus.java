package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the appointmentstatus database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Appointmentstatus.findAll", query="SELECT a FROM Appointmentstatus a"),
	@NamedQuery(name="Appointmentstatus.findAllStatusNames", query="SELECT a.appointmentStatusName FROM Appointmentstatus a")
})
public class Appointmentstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int appointmentStatusId;

	private String appointmentStatusName;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="appointmentstatus")
	private List<Appointment> appointments;

	public Appointmentstatus() {
	}

	public int getAppointmentStatusId() {
		return this.appointmentStatusId;
	}

	public void setAppointmentStatusId(int appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}

	public String getAppointmentStatusName() {
		return this.appointmentStatusName;
	}

	public void setAppointmentStatusName(String appointmentStatusName) {
		this.appointmentStatusName = appointmentStatusName;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setAppointmentstatus(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setAppointmentstatus(null);

		return appointment;
	}

}