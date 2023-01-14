package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll(); // SELECT * FROM student
    }

    public void createStudent(Student student) {
        if(studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email is already exist");
        }
        studentRepository.save(student);
    }
}
