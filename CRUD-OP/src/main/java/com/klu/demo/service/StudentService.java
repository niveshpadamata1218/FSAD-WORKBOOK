package com.klu.demo.service;
import com.klu.demo.repo.*;
import com.klu.demo.model.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final Studentrepo rep;

    public StudentService(Studentrepo rep) {
        this.rep = rep;
    }

    public Student save(Student student) {
        return rep.save(student);
    }

    public List<Student> getAll() {
        return rep.findAll();
    }

    public Student getOne(Integer id) {
        return rep.findById(id).orElse(null);
    }

    public Student update(Integer id, Student p) {
        p.setId(id);   // <-- make sure this method exists
        return rep.save(p);
    }

    public void delete(Integer id) {
        rep.deleteById(id);
    }
}
