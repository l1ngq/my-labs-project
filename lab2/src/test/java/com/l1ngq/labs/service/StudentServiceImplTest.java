package com.l1ngq.labs.service;

import com.l1ngq.labs.entity.Student;
import com.l1ngq.labs.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentServiceImplTest {

    private StudentRepository repository;
    private StudentService service;

    @BeforeEach
    void setUp() {
        repository = mock(StudentRepository.class);
        service = new StudentServiceImpl(repository);
    }

    @Test
    void testSave() {
        when(repository.save(any(Student.class))).thenReturn(7);

        int id = service.save("Ivan", 4.5);

        assertEquals(7, id);
        verify(repository).save(any(Student.class));
    }

    @Test
    void testFindByIdFound() {
        Student student = new Student(1, "Ivan", 4.0);
        when(repository.findById(1)).thenReturn(student);

        Student result = service.findById(1);

        assertEquals(student, result);
    }

    @Test
    void testFindByIdNotFound() {
        when(repository.findById(1)).thenReturn(null);

        assertThrows(StudentNotFoundException.class, () -> service.findById(1));
    }

    @Test
    void testFindByFieldFound() {
        Student first = new Student(1, "Ivan", 4.0);
        Student second = new Student(2, "Petr", 3.8);
        when(repository.findAll()).thenReturn(List.of(first, second));

        Student result = service.findByField("Petr");

        assertEquals(second, result);
    }

    @Test
    void testFindByFieldNotFound() {
        Student first = new Student(1, "Ivan", 4.0);
        when(repository.findAll()).thenReturn(List.of(first));

        assertThrows(StudentNotFoundException.class, () -> service.findByField("Egor"));
    }

    @Test
    void testFindAll() {
        Student first = new Student(1, "Ivan", 4.0);
        Student second = new Student(2, "Petr", 3.8);
        when(repository.findAll()).thenReturn(List.of(first, second));

        List<Student> result = service.findAll();

        assertEquals(2, result.size());
        assertEquals(first, result.get(0));
        assertEquals(second, result.get(1));
    }

    @Test
    void testUpdateOk() {
        Student student = new Student(1, "Ivan", 5.0);
        when(repository.update(student)).thenReturn(true);

        service.update(student);

        verify(repository).update(student);
    }

    @Test
    void testUpdateNotFound() {
        Student student = new Student(10, "Unknown", 3.0);
        when(repository.update(student)).thenReturn(false);

        assertThrows(StudentNotFoundException.class, () -> service.update(student));
    }

    @Test
    void testDeleteByIdOk() {
        Student student = new Student(3, "Ivan", 4.2);
        when(repository.findById(3)).thenReturn(student);

        service.deleteById(3);

        verify(repository).deleteById(3);
    }

    @Test
    void testDeleteByIdNotFound() {
        when(repository.findById(3)).thenReturn(null);

        assertThrows(StudentNotFoundException.class, () -> service.deleteById(3));
        verify(repository, never()).deleteById(3);
    }
}

