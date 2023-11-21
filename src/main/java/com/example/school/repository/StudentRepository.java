// Write your code here
package com.example.school.repository;

import java.util.*;
import com.example.school.model.*;

public interface StudentRepository {
    List<Student> getAllStudents();

    Student addStudent(Student student);

    int addStudents(List<Student> students);

    Student getStudentById(int studentId);

    Student updateStudent(int studentId, Student student);

    void deleteStudent(int studentId);
}
