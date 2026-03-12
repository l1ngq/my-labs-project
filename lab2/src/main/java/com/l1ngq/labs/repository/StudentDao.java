package com.l1ngq.labs.repository;


import com.l1ngq.labs.entity.Student;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;

@RegisterRowMapper(StudentMapper.class)
public interface StudentDao {

    // 1. Create
    @SqlUpdate("INSERT INTO student (name, grade) VALUES (:name, :grade)")
    @GetGeneratedKeys
    int save(@Bind("name") String name,
             @Bind("grade") double grade);

    // 2. Read by ID
    @SqlQuery("SELECT id, name, grade FROM student WHERE id = :id")
    Student findById(@Bind("id") int id);

    // 3. Read all
    @SqlQuery("SELECT id, name, grade FROM student")
    List<Student> findAll();

    // 4. Update
    @SqlUpdate("UPDATE student SET name = :name, grade = :grade WHERE id = :id")
    int update(@Bind("id") int id, @Bind("name") String name, @Bind("grade") double grade);

    // 5. Delete
    @SqlUpdate("DELETE FROM student WHERE id = :id")
    void deleteById(@Bind("id") int id);
}
