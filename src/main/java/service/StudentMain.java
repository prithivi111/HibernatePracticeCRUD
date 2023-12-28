/*1. create student pojo class (marks need to be there )
2.  store into the database;
3. fetch all student records and store inside the arraylist;
4. from the arraylist need to filter out the student having marks greater than 90%
5. and prepare a final list of students who are achieving more than 90%

*/
package service;

import DAO.StudentDAO;

public class StudentMain {
	public static void main(String[] args) {
		
		//insert record into database
		StudentDAO.insertIntoDB();
		
		//fetch all the record from the database;
		StudentDAO.fetchFromDB();
		
		//fetch the record whose roll no is 5. *****Using Session's getMethod(class name, primary key)
		StudentDAO.fetchSpecificRecordFromDB11();
		
		//fetch the record whose roll no is 5 ******Using HQL query and uniqueResult() method
		StudentDAO.fetchSpecificRecordFromDB22();
		
		//fetch the record whose roll no is 5 ******Using HQL query and getSingleResult() method
		StudentDAO.fetchSpecificRecordFromDB33();
		
		//fetch the record whose marks is greater than 80
		StudentDAO.fetchMultipleRecordFromDB();
		
		//fetch the multiplecolums of the record
		StudentDAO.fetchMultipleColumnsFromOneRecord();
		
		//update the record (set name to brilliant whose marks is greater than 90)
		StudentDAO.fetchAndUpdateRecord();
		
		//delete the record (having name as ramram)
		StudentDAO.deleteFromDB();
		
	}

}
