package com.project.servicedesk.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employeeId;

	private String countryOfBirth;

	private Timestamp created;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Temporal(TemporalType.DATE)
	private Date dateOfJoining;

	@Temporal(TemporalType.DATE)
	private Date dateOfLeaving;

	private String employeeNumber;

	private String firstName;

	private String fullName;

	private String gender;

	private String lastName;

	private String middleName;

	private String nationality;

	private String title;

	//bi-directional many-to-one association to Appointment
	@OneToMany(mappedBy="employee", cascade=CascadeType.ALL)
	private List<Appointment> appointments;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="departmentId")
	private Department department;

	//bi-directional one-to-one association to EmpAddress
	@OneToOne(mappedBy="employee", cascade=CascadeType.ALL)
	private EmpAddress empAddress;

	//bi-directional one-to-one association to EmpDetail
	@OneToOne(mappedBy="employee", cascade=CascadeType.ALL)
	private EmpDetail empDetail;

	//bi-directional one-to-one association to EmpPhotograph
	@OneToOne(mappedBy="employee", cascade=CascadeType.ALL)
	private EmpPhotograph empPhotograph;

	//bi-directional many-to-one association to Employeeposition
	@ManyToOne
	@JoinColumn(name="roleId")
	private Employeeposition employeeposition;

	//bi-directional one-to-one association to Useraccount
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userName")
	private Useraccount useraccount;

	public Employee() {
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getCountryOfBirth() {
		return this.countryOfBirth;
	}

	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfJoining() {
		return this.dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getDateOfLeaving() {
		return this.dateOfLeaving;
	}

	public void setDateOfLeaving(Date dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}

	public String getEmployeeNumber() {
		return this.employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}
	
	public boolean empAppointmentsIsNotEmpty() {
		return this.getAppointments().size() > 0;
	}
	
	public boolean empAppointmentsIsEmpty() {
		return this.getAppointments().size() == 0;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setEmployee(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setEmployee(null);

		return appointment;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public EmpAddress getEmpAddress() {
		return this.empAddress;
	}

	public void setEmpAddress(EmpAddress empAddress) {
		this.empAddress = empAddress;
	}

	public EmpDetail getEmpDetail() {
		return this.empDetail;
	}

	public void setEmpDetail(EmpDetail empDetail) {
		this.empDetail = empDetail;
	}

	public EmpPhotograph getEmpPhotograph() {
		return this.empPhotograph;
	}

	public void setEmpPhotograph(EmpPhotograph empPhotograph) {
		this.empPhotograph = empPhotograph;
	}

	public Employeeposition getEmployeeposition() {
		return this.employeeposition;
	}

	public void setEmployeeposition(Employeeposition employeeposition) {
		this.employeeposition = employeeposition;
	}

	public Useraccount getUseraccount() {
		return this.useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}

}