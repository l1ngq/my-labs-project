package com.l1ngq.labs.service;

import com.l1ngq.labs.entity.Student;

import java.util.List;

public interface StudentService {
    // Создать студента и вернуть его id
    int save(String name, double grade);

    // Найти по id, если нет — бросить исключение
    Student findById(int id);

    // Найти по имени, если нет — бросить исключение
    Student findByField(String field);

    List<Student> findAll();

    // Обновить, если нет — бросить исключение
    void update(Student student);

    // Удалить по id
    void deleteById(int id);
}

