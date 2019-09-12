package fr.formation.inti.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	private Integer empId;	
	private String firstName;	
	private String lastName;	
	private String title;
	private Employee manager;	
	private Date endDate;	
	private Date startDate;
	private Department department;
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Employee() {
	}
	
	public Employee(String firstName, String lastName, Date startDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.startDate = startDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID")
	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPERIOR_EMP_ID")
	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID", nullable = true,
			foreignKey = @ForeignKey(name="EMPLOYEE_DEPARTMENT_FK"),referencedColumnName = "DEPT_ID")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy ="manager")
	public Set<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	@Column(name = "FIRST_NAME", length = 20, nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "LAST_NAME", length = 20, nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", nullable = false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", startDate="
				+ startDate + "]";
	}

}