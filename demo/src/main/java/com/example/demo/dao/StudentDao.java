package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Student;

public interface StudentDao {
	
	public List<Student> findAll();	//abstract method must be implemented by implementing class
	public void save(Student theStudent);
	public void deleteById(int id);
	public Student getById(int id);
}
