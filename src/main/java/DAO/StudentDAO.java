package DAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Student;
import util.StudentSession;

public class StudentDAO {
	
	private static SessionFactory sf = StudentSession.initializeSession();
	
	public static void insertIntoDB() {	
		//Create a session from SessionFactory
		Session session1 = sf.openSession();	
		Transaction tx1 = null;
		try {
			
			Scanner sc = new Scanner(System.in);
		
			List<Student> students = new ArrayList<Student>();

			System.out.println("Enter the no. of student records you want to record in the DB");
			int noOfRecords = sc.nextInt();
		
			int count=0;
			for(int i=0; i<noOfRecords; i++) {
			System.out.println("Scanning " + ++count + " record details.");
			
			Student student = new Student();
			
			System.out.println("Enter name");			
			student.setName(sc.next());
			System.out.println("Enter marks");
			student.setMarks(sc.nextInt());
			
			students.add(student);	
			}
			//Create transaction from a session
			tx1 = session1.beginTransaction();	
			for(Student std : students) {
			session1.save(std);
			}
			System.out.println(count + " student records successfully saved in the DB.");
			tx1.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			tx1.rollback();
		}finally {
			if (session1 != null) {
				session1.close();
			}
		}
	}

	public static void fetchFromDB(){
		//Create a session from SessionFactory
		Session session2 = sf.openSession();	
		Transaction tx2 = null;
		
		try 
		{
		//Create transaction from a session
		tx2 = session2.beginTransaction();	
		
		//Using HQL query to fetch all the records
		Query query = session2.createQuery("from Student");
		List<Student> students = query.list();
		for(Student std : students) {
			System.out.print(std.getName() + " | " +std.getRollno() + " | " + std.getMarks());
			System.out.println();
		}
		
		System.out.println("All records successfully fetched from the DB.");
		tx2.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx2.rollback();	
		} finally {
			if (session2 != null) {
				session2.close();
			}
		}
	}
	
	public static void fetchSpecificRecordFromDB11() {
		//Create a session from SessionFactory
		Session session311 = sf.openSession();	
		Transaction tx311 = null;
	
		try 
		{
		//Create transaction from a session
		tx311 = session311.beginTransaction();	
		
		//Since roll no is primary key and only one record to fetch, get method is used.
		Student std = new Student();
		std = session311.get(Student.class, 1);
		System.out.println(std.getName() + " | " +std.getRollno() + " | " + std.getMarks());
	
		System.out.println("The record having rollno as 1 is successfully fetched.");	
		tx311.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			tx311.rollback();	
		} finally {
			if (session311 != null) {
				session311.close();
			}
		}
	}
	
	public static void fetchSpecificRecordFromDB22() {
		//Create a session from SessionFactory
		Session session322 = sf.openSession();	
		Transaction tx322 = null;
		
		try {
		tx322 = session322.beginTransaction();		
		Query query = session322.createQuery("from Student where rollno=2");
		Student std = (Student) query.uniqueResult();
		System.out.println(std.getName() + " | " +std.getRollno() + " | " + std.getMarks());
		
		System.out.println("The record having rollno as 2 is successfully fetched.");
		tx322.commit();
		}
		catch(Exception e) {
		e.printStackTrace();
		tx322.rollback();
		} finally {
			if (session322 != null) {
				session322.close();
			}
		}
		
	}
	
	public static void fetchSpecificRecordFromDB33() {
		//Create a session from SessionFactory
		Session session333 = sf.openSession();	
		Transaction tx333 = null;
		
		try {
		tx333 = session333.beginTransaction();
		Query query = session333.createQuery("from Student where rollno =:value");
			query.setParameter("value", "3");
			Student std =(Student) query.getSingleResult();	
		System.out.println(std.getName() + " | " +std.getRollno() + " | " + std.getMarks());
	
		System.out.println("The record having rollno as 3 is successfully fetched.");
		tx333.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx333.rollback();
		}finally {
			if (session333 != null) {
				session333.close();
			}
		}
		
	}
	
	public static void fetchMultipleRecordFromDB() {
		//Create a session from SessionFactory
		Session session4 = sf.openSession();	
		Transaction tx4 = null;
		try {
		tx4 = session4.beginTransaction();
		
		//Using HQL query to fetch multiple records (here 'marks' is not column name)
		 Query query = session4.createQuery("from Student where marks > :value");
		  	query.setParameter("value", "80");
		  	List<Student> students = query.list();
		 		for(Student std : students) {
		 			System.out.print(std.getName() + " | "+std.getRollno() + " | " + std.getMarks()); 
		 			System.out.println(); 
		 		}
		System.out.println("The records having marks>80 are successfully fetched.");
		tx4.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx4.rollback();
		}finally {
			if (session4 != null) {
				session4.close();
			}
		}
	}
	
	
	public static void fetchMultipleColumnsFromOneRecord() {
		//Create a session from SessionFactory
		Session session44 = sf.openSession();	
		Transaction tx44 = null;
		try {
		tx44 = session44.beginTransaction();
		
		//Using HQL query to fetch multiple columns
		Query q = session44.createQuery("select name, marks from Student s where s.rollno=2");
		
		//Since we are not an object here, we are getting only the properties(name and marks) of the Student's object.
		//Since there are two data type values(int and string) we have to store that in an array of type Object,
		//and we are typecasting.
			Object[] result = (Object[]) q.uniqueResult();
			
			if(result!=null) {
				String name = (String) result[0];
				int marks = (Integer) result[1];
				
				System.out.println("Name: " + name);
				System.out.println("Marks: " + marks);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			tx44.rollback();
		}finally {
			if (session44 != null) {
				session44.close();
			}
		}
	}
	
	//What if we are fetching the multiple columns with dynamic value like rollno>2?
	//in that case, the answer will be like:
	
	//Query q = session44.createQuery("select name, marks from Student s where s.rollno>2)
	
	//As we are getting the list of name and marks now, so the we have to use the list here/
	//List<Object[]> result = (List<Object[>]) q.list()    //we cannot use uniqueResult(), as we are not getting one value here
	//for(Object[] rr: result){
	//   syso (rr[0] + " : " + rr[1} )
	//	}
	
	//Please check it once.
	
	
	
	
	
	
	
	public static void fetchAndUpdateRecord() {
		//Create a session from SessionFactory
		Session session5 = sf.openSession();	
		Transaction tx5 = null;
		
		try {
		tx5 = session5.beginTransaction();
		
		//Using HQL query to update the record set name as Mayalu, marks as 100, where roll is 1
		Query query = session5.createQuery("update Student set name= :value1, " + "marks= :value2 where rollno =:value3");
		query.setParameter("value1", "Winner");
		query.setParameter("value2", "100");
		query.setParameter("value3", "1");
		
		int rowsUpdate =query.executeUpdate();
		System.out.println("Rows updated: " + rowsUpdate);
		tx5.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx5.rollback();
		} finally {
			if (session5 != null) {
				session5.close();
			}
		}
		
	}
	
	public static void deleteFromDB(){
		//Create a session from SessionFactory
		Session session6 = sf.openSession();	
		Transaction tx6 = null;
		
		try {
			tx6 = session6.beginTransaction();
			
			//you can use the method load() instead of get() also. But there is only slight difference in using load().
			Student std =(Student)session6.get(Student.class, 2);
			
			if(std != null) {
				session6.delete(std);
				System.out.println("Record having (primarykey)rollno 2 deleted successfully: ");
			} else {
				System.out.println("Record with the specified primary key not found");
			}	
			tx6.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			tx6.rollback();
		}finally {
			if (session6 != null) {
				session6.close();
			}
		}
	}
}
