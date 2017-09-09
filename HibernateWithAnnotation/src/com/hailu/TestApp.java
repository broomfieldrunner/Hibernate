package com.hailu;
import com.hailu.util.HibernateUtil;
import com.hailu.Student;

import java.io.IOException;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class TestApp {
	
public static void main(String[] args) throws IOException{
		
		try {
			TestApp ta = new TestApp();

			Scanner s = new Scanner(System.in);
			int option = 0;

			System.out
					.println("\n 1.Create \n 2.Update \n 3.Read \n 4.Delete \n 5.Exit  \nEnter Your Option :  ");
			option = s.nextInt();

			switch (option) {
			case 1:
				System.out.println("Enter Id  \n");
				int studentId = s.nextInt();
				System.out.println("Enter Student Name \n");

				String studentName = s.next();
				System.out.println("Student Grade  \n");
				int studentGrade = s.nextInt();
				ta.createStudent(studentId, studentName,studentGrade);
				break;
			case 2:
				System.out.println("Enter Id  \n");
				int updateId = s.nextInt();
				System.out.println("Enter Student Name  \n");
				String updateStudentName = s.next();
				ta.updateStudent(updateId, updateStudentName);
				break;
			case 3:
				System.out.println("Enter Id  \n");
				int readId = s.nextInt();
				ta.readStudentDetails(readId);
				break;
			case 4:
				System.out.println("Enter Id  \n");
				int deleteId = s.nextInt();
				ta.detleteStudent(deleteId);
				break;
			case 5:
				System.out.println("Exit  \n");
				return;

			default:
				System.out.println("Please Provide a Valid Option  \n");
				break;
			}
		} finally {
			HibernateUtil.shutDown();
		}
		
	}
	
	public void createStudent(int studentId, String studentName,int studentGrade) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Student student = new Student();
			student.setStudentId(studentId);
			student.setStudentName(studentName);
			student.setStudentGrade(studentGrade);
			session.save(student);
			transaction.commit();
			System.out.println("Details Added Successfully");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void readStudentDetails(int id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Student student = (Student) session.get(Student.class, id);
			System.out.println("Student Details : " + student.getStudentName());
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public void updateStudent(int id, String studentName) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Student student = (Student) session.get(Student.class, id);
			student.setStudentName(studentName);
			session.update(student);
			transaction.commit();
			System.out.println("Student Updated Successfully ..!");
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void detleteStudent(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Student student = (Student) session.get(Student.class, id);
			student.setStudentId(id);
			session.delete(student);
			System.out.println("Student deleted Successfully ..!");
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			;
		} finally {
			session.close();
		}

	}

}
