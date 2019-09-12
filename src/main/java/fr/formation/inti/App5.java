package fr.formation.inti;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.print.attribute.standard.Finishings;

import fr.formation.inti.entities.Employee;

public class App5 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction tx = em.getTransaction();
        
        Employee emp = em.find(Employee.class, 39);
        
        em = emf.createEntityManager();
        tx.begin();
        em.remove(emp);
        tx.commit();
        
        em.close();
        em.close();
        emf.close();
        
	}

}
