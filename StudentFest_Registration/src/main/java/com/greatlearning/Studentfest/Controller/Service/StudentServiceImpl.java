package com.greatlearning.Studentfest.Controller.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.greatlearning.Studentfest.Entity.Student;
import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionfactory;
	// create session
	private Session session;

	public StudentServiceImpl(SessionFactory sessionfactory) {
		super();
		this.sessionfactory = sessionfactory;
		try {
			session = sessionfactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionfactory.openSession();

		}
	}

	
	@Override
	public List<Student> findAll() {

		// TODO Auto-generated method stub

		Transaction transaction = session.beginTransaction();
		// find all the records from the database table
		List<Student> students = session.createQuery("from Student").list();
		transaction.commit();
		

		return students;
	}

	@Override
	public void save(Student theStudent) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		// save transaction
		session.saveOrUpdate(theStudent);
		transaction.commit();
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		// get transaction
		Student student = session.get(Student.class, theId);
		// delete record
		session.delete(student);
		transaction.commit();
	}

	@Override
	public Student findById(int theId) {
		// TODO Auto-generated method stub
		Transaction transaction = session.beginTransaction();
		// find record with Id from the database table
		Student student = session.get(Student.class, theId);
		transaction.commit();
		return student;
	}

}
