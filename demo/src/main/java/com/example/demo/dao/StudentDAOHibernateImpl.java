package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Student;

@Repository		//creates the DAO dependency bean to be injected in REST CONTROLLER 
public class StudentDAOHibernateImpl implements StudentDao {
	@Autowired
	public EntityManager entityManager;		//dependency bean(of EntityManager) injected
	
	@Override
	public List<Student> findAll() {
//		get current hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
//		create a query 
		List<Student> students=
				currentSession.createQuery("from Student",Student.class).getResultList();
//		execute a query and get result list(List type )
			//	List<Student> students=theQuery.getResultList();
//		return result
		return students;
	}
	@Transactional		//applied while making changes to database
	public void save(Student theStudent) {
		Session currentSession=entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theStudent);
		
		
	}
	@Transactional		//applied while making changes to database
	public void deleteById(int theid) {
		Session currentSession=entityManager.unwrap(Session.class);
		Query theQuery=currentSession.createQuery("delete from student where id=:studentId");
		theQuery.setParameter("studentId",theid);
		theQuery.executeUpdate();
	}
	public Student getById(int id) {
		Session currentSession=entityManager.unwrap(Session.class);
		Student theStudent=currentSession.get(Student.class,id);
		return theStudent;
		
	}

}
