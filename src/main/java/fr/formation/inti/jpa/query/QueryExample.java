package fr.formation.inti.jpa.query;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.formation.inti.entities.Employee;

public class QueryExample {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");

	public static void main(String[] args) {
		try {
			persistEmployees();
			findAllEmployees();
			updateAdminEmployees();
			findAllEmployees();
//			updateAllEmployeesSalaries();
//			findAllEmployees();
		} finally {
			entityManagerFactory.close();
		}
	}

	public static void persistEmployees() {
		Employee employee1 = new Employee("Diana", "Diana",new Date());
		Employee employee2 = new Employee("Rose", "eee", new Date());

		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(employee1);
		em.persist(employee2);


		em.getTransaction().commit();
		em.close();
	}

	private static void findAllEmployees() {
		EntityManager em = entityManagerFactory.createEntityManager();
		System.out.println("-- All employees --");
		Query query = em.createQuery("SELECT e FROM Employee e");
		List<Employee> resultList = query.getResultList();
		resultList.forEach(System.out::println);
		em.close();
	}

	private static void updateAdminEmployees() {
		System.out.println("-- update Administration employees firstName by firstName_U --");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("UPDATE Employee e SET e.firstName = concat(e.firstName, :name) WHERE e.department.deptId = :dept");
		query.setParameter("name", "_U");
		query.setParameter("dept", 3);
		int rowsUpdated = query.executeUpdate();
		System.out.println("entities Updated : " + rowsUpdated);
		em.getTransaction().commit();
		em.close();
	}

	private static void updateAllEmployeesSalaries() {
		System.out.println("-- increasing all employees salaries by different percentage --");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery(
				"UPDATE Employee e SET e.salary = " + " CASE " + " WHEN e.dept = 'IT' THEN (e.salary * 1.20) "
						+ " WHEN e.dept = 'Admin' THEN (e.salary * 1.15) " + " ELSE (e.salary * 1.12) " + " END");
		int rowsUpdated = query.executeUpdate();
		System.out.println("entities Updated: " + rowsUpdated);
		em.getTransaction().commit();
		em.close();
	}
}
