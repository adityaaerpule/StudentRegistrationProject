package com.studentregistration.service;

import java.util.List;
import com.studentregistration.dao.StudentDAO;
import com.studentregistration.model.Student;

public class StudentService {
    private StudentDAO studentDAO;

    // Setter for StudentDAO
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void registerStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
}
