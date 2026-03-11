package com.l1ngq.labs.repository;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student map(ResultSet rs, StatementContext ctx) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        double grade = rs.getDouble("grade");
        return new Student(id, name, grade);
    }
}
