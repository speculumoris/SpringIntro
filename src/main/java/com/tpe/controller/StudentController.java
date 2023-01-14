package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students") //http://localhost:8080/students
public class StudentController  {
    @Autowired
    private StudentService studentService;
    // !!! Bütün öğrenciler gelsin
    @GetMapping // http://localhost:8080/students + GET
    public ResponseEntity<List<Student>> getAll(){
        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students); // 200 kodunu HTTP Status kodu olarak gönderir
    }
    // !!! Student objesi oluşturalım
    @PostMapping  // http://localhost:8080/students + POST + JSON
    public ResponseEntity<Map<String,String>> createStudent(@Valid  @RequestBody Student student) {
        // @Valid : parametreler valid mi kontrol eder, bu örenekte Student
        //objesi oluşturmak için  gönderilen fieldlar yani
        //name gibi özellikler düzgün set edilmiş mi ona bakar.
        // @RequestBody = gelen parametreyi, requestin bodysindeki bilgisi ,
        //Student objesine map edilmesini sağlıyor.

        studentService.createStudent(student);

        Map<String,String> map = new HashMap<>();
        map.put("message","Student is created successfuly");
        map.put("status" ,"true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);  // 201
    }



}
