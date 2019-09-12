package fr.formation.inti.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.formation.inti.entities.Employee;

/**
 * Notre classe EmployeeDaoImpl suit le modèle de conception Singleton qui
 * garantit qu'une seule instance de cette classe sera créée dans l'application.
 * Lors de la première création de classe, la méthode getEntityManager () est
 * chargée de créer une instance de EntityManager.
 * 
 * @author yahya
 *
 */
public class EmployeeDaoImpl implements IEmployeeDao {
	private static IEmployeeDao instance;
	protected EntityManager em;

	public static IEmployeeDao getInstance() {
		if (instance == null) {
			instance = new EmployeeDaoImpl();
		}

		return instance;
	}

	private EmployeeDaoImpl() {
		em = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
		if (em == null) {
			em = factory.createEntityManager();
		}

		return em;
	}

	@Override
	public List<Employee> getAll() {
		Query q = em.createQuery("FROM " + Employee.class.getName());
		List<Employee> employees = q.getResultList();
		return employees;
	}

	@Override
	public Integer save(Employee e) {
		try {
			em.getTransaction().begin();
			em.persist(e);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}
		return e.getEmpId();
	}

	@Override
	public void update(Employee e) {
		try {
			em.getTransaction().begin();
			em.merge(e);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@Override
	public void delete(Employee e) {
		try {
			em.getTransaction().begin();
			e = em.find(Employee.class, e.getEmpId());
			em.remove(e);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@Override
	public Employee findById(Integer id) {
		return em.find(Employee.class, id);
	}

	@Override
	public void deleteByID(Integer id) {

		try {
			Employee e = findById(id);
			delete(e);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	
	public void close() {
		em.close();
	}

}
