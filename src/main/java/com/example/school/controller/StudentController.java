/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */
package com.example.school.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.school.service.*;
import com.example.school.model.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentH2Service studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PostMapping("/bulk")
    public String addStudents(@RequestBody ArrayList<Student> students) {
        int count = studentService.addStudents(students);
        return ("Successfully added " + count + " students");
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        return studentService.updateStudent(studentId, student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable int studentId) {
        studentService.deleteStudent(studentId);
    }
}
