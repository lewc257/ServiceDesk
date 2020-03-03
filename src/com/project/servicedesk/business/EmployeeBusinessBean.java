package com.project.servicedesk.business;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.project.servicedesk.entity.Appointment;
import com.project.servicedesk.entity.Employee;
import com.project.servicedesk.service.EmployeeServiceBean;

import javax.ejb.EJB;
/*
Note:
You can make your bean passivation capable by implementing the Serializable interface
*/

@Named(value="employeeBusinessBean")
@SessionScoped
public class EmployeeBusinessBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	EmployeeServiceBean employeeServiceBean;
	
	DetailEntry entry;
	
	Employee selectedEmployee;
	
	public DetailEntry getDetailEntry() {
		return entry;
	}
	
	public String employeeEditForm(Employee employee) {
		selectedEmployee = employee;
		entry = new DetailEntry();
		entry.setUsername(employee.getUseraccount().getUserName());
		entry.setPassword(employee.getUseraccount().getUserPassword());
		entry.setTitle(employee.getTitle());
		entry.setFirstName(employee.getFirstName());
		entry.setMiddleName(employee.getMiddleName());
		entry.setLastName(employee.getLastName());
		entry.setPersonalEmail(employee.getEmpDetail().getPersonalEmail());
		entry.setAddressLine1(employee.getEmpAddress().getAddress1());
		entry.setAddressLine2(employee.getEmpAddress().getAddress2());
		entry.setStreet(employee.getEmpAddress().getStreet());
		entry.setCity(employee.getEmpAddress().getCity());
		entry.setPostcode(employee.getEmpAddress().getPostcode());
		entry.setCountry(employee.getEmpAddress().getCountry());

		return "employee_edit";
	}

	public String editEmployee() {
		selectedEmployee.getUseraccount().setUserPassword(entry.getPassword());
		selectedEmployee.setTitle(entry.getTitle());
		selectedEmployee.setFirstName(entry.getFirstName());
		selectedEmployee.setMiddleName(entry.getMiddleName());
		selectedEmployee.setLastName(entry.getLastName());
		selectedEmployee.getEmpDetail().setPersonalEmail(entry.getPersonalEmail());
		selectedEmployee.getEmpAddress().setAddress1(entry.getAddressLine1());
		selectedEmployee.getEmpAddress().setAddress2(entry.getAddressLine2());
		selectedEmployee.getEmpAddress().setStreet(entry.getStreet());
		selectedEmployee.getEmpAddress().setCity(entry.getCity());
		selectedEmployee.getEmpAddress().setPostcode(entry.getPostcode());
		selectedEmployee.getEmpAddress().setCountry(entry.getCountry());

		employeeServiceBean.editEmployee(selectedEmployee);

		return "employee_dashboard";
	}
}
