package com.klu.demo.repo;
import com.klu.demo.model.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Studentrepo extends JpaRepository<Student,Integer> {

}
