package com.l1ngq.labs.repository;

import com.l1ngq.labs.entity.Student;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private final StudentDao dao;

    public StudentRepositoryImpl(Jdbi jdbi) {
        jdbi.installPlugin(new SqlObjectPlugin());
        this.dao = jdbi.onDemand(StudentDao.class);
    }

    @Override
    public int save(Student student) {
        try {
            return dao.save(student.getName(), student.getGrade());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save student", e);
        }
    }

    @Override
    public Student findById(int id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch student by id", e);
        }
    }

    @Override
    public List<Student> findAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all students", e);
        }
    }

    @Override
    public boolean update(Student student) {
        try {
            int updatedRows = dao.update(student.getId(), student.getName(), student.getGrade());
            return updatedRows > 0;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update student", e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            dao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete student", e);
        }
    }
}