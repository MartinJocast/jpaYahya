package fr.formation.inti;

import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.formation.inti.entities.Department;
import fr.formation.inti.entities.Employee;

/**
 * Hello world!
 *
 */
public class App2 
{
    public static void main( String[] args )
    {
        System.out.println( "Running App..." );
        
        System.out.println("Create persistence manager");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Create Entities : employee");
        
        

        
        System.out.println("Persist entities in a transaction ");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Department dept = em.find(Department.class, 1);
       
        
        transaction.commit();
        
        System.out.println("Close EntityManager");
        
//        emp = em.find(Employee.class, 5);
        
//        System.out.println(emp);
        
        em.close();
        emf.close();
    }
}
