package com.l1ngq.labs.repository;

import java.util.List;


public interface StudentRepository {
    // Create (возвращает id созданной записи)
    int save(Student student);
    // Read (возвращает null, если запись не найдена)
    Student findById(int id);
    List<Student> findAll();
    // Update (используйте id для поиска сущности, возвращайте false, если запись не найдена)
    boolean update(Student student);
    // Delete
    void deleteById(int id);
}