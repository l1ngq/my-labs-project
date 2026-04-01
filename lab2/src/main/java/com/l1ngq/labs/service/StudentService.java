package com.l1ngq.labs.service;

import com.l1ngq.labs.entity.Student;

import java.util.List;

public interface StudentService {
    int save(String name, double grade);

    Student findById(int id);

    Student findByField(String field);

    List<Student> findAll();

    void update(Student student);

    void deleteById(int id);
}

