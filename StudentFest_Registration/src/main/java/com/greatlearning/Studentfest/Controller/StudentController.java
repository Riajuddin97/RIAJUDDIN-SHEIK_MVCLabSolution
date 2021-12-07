package com.greatlearning.Studentfest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.Studentfest.Controller.Service.StudentService;
import com.greatlearning.Studentfest.Entity.Student;

@Controller
//add mapping for "/students"
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// add mapping for "/list"
	@RequestMapping("/list")
	public String listStudents(Model model) {
		// get Students from db
		List<Student> students = studentService.findAll();
		// add to the spring model
		model.addAttribute("Students", students);
		return "list-students";

	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		// create model attribute to bind form data
		Student student = new Student();
		model.addAttribute("Student", student);
		return "student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		// get the student from the service
		Student student = new Student();
		// set student as a model attribute to pre-populate the form
		student = studentService.findById(id);
		model.addAttribute("Student", student);
		// send over to our form
		return "student-form";

	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String fname,
			@RequestParam("lastName") String lname, @RequestParam("course") String course,
			@RequestParam("country") String country) {
		System.out.println(id);
		Student student;
		if (id != 0) {
			student = studentService.findById(id);
			student.setFirstName(fname);
			student.setLastName(lname);
			student.setCourse(course);
			student.setCountry(country);
		} else
			student = new Student(fname, lname, course, country);
		// save the Student
		studentService.save(student);

		// use a redirect to prevent duplicate submissions
		return "redirect:/student/list";

	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		// delete the Book
		studentService.deleteById(id);
		// redirect to /Books/list
		return "redirect:/student/list";
	}
}
