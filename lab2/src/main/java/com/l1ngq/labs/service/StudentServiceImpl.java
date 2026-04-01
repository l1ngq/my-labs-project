package com.l1ngq.labs.service;

import com.l1ngq.labs.entity.Student;
import com.l1ngq.labs.repository.StudentRepository;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public int save(String name, double grade) {
        Student student = new Student(0, name, grade);
        return repository.save(student);
    }

    @Override
    public Student findById(int id) {
        Student student = repository.findById(id);
        if (student == null) {
            throw new StudentNotFoundException("Студент с id=" + id + " не найден");
        }
        return student;
    }

    @Override
    public Student findByField(String field) {
        List<Student> students = repository.findAll();

        for (Student student : students) {
            if (student.getName().equals(field)) {
                return student;
            }
        }

        throw new StudentNotFoundException("Студент с именем '" + field + "' не найден");
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Student student) {
        boolean updated = repository.update(student);
        if (!updated) {
            throw new StudentNotFoundException("Студент с id=" + student.getId() + " не найден");
        }
    }

    @Override
    public void deleteById(int id) {
        Student existingStudent = repository.findById(id);
        if (existingStudent == null) {
            throw new StudentNotFoundException("Студент с id=" + id + " не найден");
        }

        repository.deleteById(id);
    }
}

