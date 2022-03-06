package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;

@RestController
@RequestMapping("/api")

public class StudentRestController {
	private StudentDao studentDAO;//inject the Student DAO bean
	@Autowired
	public StudentRestController(StudentDao studentDAO){
		this.studentDAO=studentDAO;
	}

	//expose "/student" end point to return list of students
	@GetMapping("/student")
	public List<Student> handlerMethod(){
		System.out.println(studentDAO.findAll().toString());
		return studentDAO.findAll();		//returns a pojo of type List
	}
	@PostMapping("/addStudent")
	public void save(@RequestBody Student theStudent){//jackson will automatically convert JSON data to pojo theStudent
		//theStudent.setId(0);				//to prompt an addition not an update of database
		studentDAO.save(theStudent);
		System.out.println(theStudent.toString());

	}
	@GetMapping("/delete/{id}")
	public void deleteId(@PathVariable int id) {
		System.out.println(studentDAO.getById(id).toString());
		studentDAO.deleteById(id);
	}

	@GetMapping("/get/{id}")
	public Student handler(@PathVariable int id) {
	return studentDAO.getById(id);	//returns a pojo
	
		
	}
}
