package com.studentregistration.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.studentregistration.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private JdbcTemplate jdbcTemplate;

    // Setter for JdbcTemplate
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, course) VALUES (?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getCourse());
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    // RowMapper for mapping ResultSet to Student object
    private static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setCourse(rs.getString("course"));
            return student;
        }
    }
}
