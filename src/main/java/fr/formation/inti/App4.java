package fr.formation.inti;

import fr.formation.inti.jpa.dao.EmployeeDaoImpl;
import fr.formation.inti.jpa.dao.IEmployeeDao;

public class App4 {

	public static void main(String[] args) {

		IEmployeeDao dao = EmployeeDaoImpl.getInstance();
		
		
		dao.findById(59);
		
		dao.close();
		
	}

}
