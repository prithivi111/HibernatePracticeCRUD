package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Student;

public class StudentSession {
	
	public static SessionFactory initializeSession() {
		try {
			Configuration con = new Configuration().configure().addAnnotatedClass(Student.class);
			SessionFactory sf= con.buildSessionFactory();
			return sf;
		} catch (Exception ex){
			throw new ExceptionInInitializerError(ex);	
		}
	}
	

}
