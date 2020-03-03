package com.project.servicedesk.interfaces;
import com.project.servicedesk.entity.*;

public interface EmployeeServiceBeanLocal {
	public void createEmployee(Employee employee);
	public void editEmployee(Employee employee);
	public void deleteEmployee(Employee employee);
}
