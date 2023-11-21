/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here
package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.school.repository.*;
import com.example.school.model.*;

@Service

public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM STUDENT";
        return db.query(sql, new StudentRowMapper());
    }

    @Override
    public Student addStudent(Student student) {
        db.update("insert into STUDENT(studentName, gender, standard) values (?,?,?)", student.getStudentName(),
                student.getGender(), student.getStandard());
        return db.queryForObject("select * from STUDENT where studentName = ? and gender = ? and standard = ?",
                new StudentRowMapper(),
                student.getStudentName(), student.getGender(), student.getStandard());
    }

    @Override
    public int addStudents(List<Student> students) {
        String sql = "INSERT INTO STUDENT (studentName, gender, standard) VALUES (?, ?, ?)";
        return db.batchUpdate(sql, students, students.size(),
                (ps, student) -> {
                    ps.setString(1, student.getStudentName());
                    ps.setString(2, student.getGender());
                    ps.setInt(3, student.getStandard());
                }).length;
    }

    @Override
    public Student getStudentById(int studentId) {
        try {
            return db.queryForObject("select * from STUDENT where studentId = ?", new StudentRowMapper(), studentId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        String sql = "UPDATE STUDENT SET studentName = ?, gender = ?, standard = ? WHERE studentId = ?";
        db.update(sql, student.getStudentName(), student.getGender(), student.getStandard(), studentId);
        student.setStudentId(studentId);
        return student;
    }

    @Override
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM STUDENT WHERE studentId = ?";
        db.update(sql, studentId);
    }
}