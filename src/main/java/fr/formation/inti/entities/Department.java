package fr.formation.inti.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
//
@Entity
@Table(name = "DEPARTMENT")
public class Department {

	private Integer deptId;
	private String name;
	
	
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Department() {
	}

	public Department(Integer deptId, String name) {
		this.deptId = deptId;
		this.name = name;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPT_ID")
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Column(name = "NAME", length = 20, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Transient
	
	
	@OrderBy("FIRST_NAME desc")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}