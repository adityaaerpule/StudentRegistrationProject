package com.studentregistration.dao;

import java.util.List;
import com.studentregistration.model.Student;

public interface StudentDAO {
	 void addStudent(Student student);
	 List<Student> getAllStudents();

}
