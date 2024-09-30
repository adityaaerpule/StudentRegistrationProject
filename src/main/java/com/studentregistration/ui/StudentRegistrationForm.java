package com.studentregistration.ui;

import javax.swing.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.studentregistration.model.Student;
import com.studentregistration.service.StudentService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentRegistrationForm extends JFrame {
	private JTextField txtName;
	private JTextField txtCourse;
	private JButton btnRegister;
	private JButton btnViewStudents;
	private StudentService studentService;

	public StudentRegistrationForm(StudentService studentService) {
		this.studentService = studentService;

		// Setting up the JFrame
		setTitle("Student Registration");
		setBounds(500, 50, 500, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null); // Use null layout

		// Set the icon image for the application window
		setAppIcon();

		// Create and set bounds for components
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(50, 50, 100, 25); // x, y, width, height
		add(lblName);

		txtName = new JTextField();
		txtName.setBounds(150, 50, 200, 25); // x, y, width, height
		add(txtName);

		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setBounds(50, 100, 100, 25); // x, y, width, height
		add(lblCourse);

		txtCourse = new JTextField();
		txtCourse.setBounds(150, 100, 200, 25); // x, y, width, height
		add(txtCourse);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(50, 150, 150, 30); // x, y, width, height
		add(btnRegister);

		btnViewStudents = new JButton("View Students");
		btnViewStudents.setBounds(220, 150, 150, 30); // x, y, width, height
		add(btnViewStudents);

		// Register button action
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String course = txtCourse.getText();
				Student student = new Student();
				student.setName(name);
				student.setCourse(course);

				studentService.registerStudent(student);
				JOptionPane.showMessageDialog(null, "Student Registered!");
			}
		});

		// View Students button action
		btnViewStudents.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viewStudents(); // Call method to view students
			}
		});
	}

	private void viewStudents() {
		List<Student> students = studentService.getAllStudents(); // Fetch students

		// Displaying students in a dialog
		if (students.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No registered students.");
			return;
		}

		StringBuilder studentList = new StringBuilder("Registered Students:\n");
		for (Student student : students) {
			studentList.append("ID: ").append(student.getId()).append(", Name: ").append(student.getName())
					.append(", Course: ").append(student.getCourse()).append("\n");
		}

		JOptionPane.showMessageDialog(this, studentList.toString());
	}

	private void setAppIcon() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/Images/Logo_Image.jpg"));
		Image image = icon.getImage();
		setIconImage(image);
	}

	public static void main(String[] args) {
		// Load Spring context and get the StudentService bean
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentService studentService = (StudentService) context.getBean("studentService");

		// Start the Swing application
		StudentRegistrationForm form = new StudentRegistrationForm(studentService);
		form.setVisible(true);
	}
}
