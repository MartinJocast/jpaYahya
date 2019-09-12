package fr.formation.inti.jpa.dao;

import java.util.List;

import fr.formation.inti.entities.Employee;

public interface IEmployeeDao {

	
	public List<Employee> getAll();
	
	public Integer save(Employee e);
	
	public void update(Employee e);
	
	public void delete(Employee e);
	
	public void deleteByID(Integer id);
	
	public Employee findById(Integer id);
	
	public void close();
}
