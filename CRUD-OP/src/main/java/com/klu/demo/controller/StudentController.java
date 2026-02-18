package com.klu.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.klu.demo.model.Student;
import com.klu.demo.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Student create(@RequestBody Student s) {
        return service.save(s);
    }

    
    // GET ALL
    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Student getOne(@PathVariable Integer id) {
        return service.getOne(id);
    }

    
    @PutMapping("/{id}")
    public Student update(@PathVariable Integer id,
                          @RequestBody Student s) {
        return service.update(id, s);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "Student deleted successfully";
    }
}
