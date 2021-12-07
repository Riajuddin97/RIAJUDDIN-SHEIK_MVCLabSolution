package com.greatlearning.Studentfest.Controller.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.Studentfest.Entity.Student;

@Service
public interface StudentService {

	public List<Student> findAll();

	public Student findById(int Id);

	public void save(Student student);

	public void deleteById(int theid);

}
