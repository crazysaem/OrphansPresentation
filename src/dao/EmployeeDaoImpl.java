package dao;

import entity.Employee;

public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements EmployeeDao {

	@Override
	protected Class<Employee> getEntityClass() {
		return Employee.class;
	}

}
