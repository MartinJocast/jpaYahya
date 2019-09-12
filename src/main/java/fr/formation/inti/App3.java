package fr.formation.inti;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.formation.inti.entities.Department;
import fr.formation.inti.entities.Voiture;

public class App3 {
	
	public static void main(String[] args) {
		System.out.println( "Running App..." );
        
        System.out.println("Create persistence manager");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
        EntityManager em = emf.createEntityManager();
        
       	Voiture v = new Voiture();
        v.setMarque("test");
        System.out.println("Persist entities in a transaction ");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(v);
        Department nord = em.find(Department.class, 7);
        transaction.commit();
        
	}

}
