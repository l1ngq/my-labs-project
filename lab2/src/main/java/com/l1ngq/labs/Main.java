package com.l1ngq.labs;

import com.l1ngq.labs.repository.*;
import org.jdbi.v3.core.Jdbi;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        // 1. Создаём пул соединений
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2. Применяем миграции Liquibase
        DatabaseMigrator migrator = new DatabaseMigrator(connectionFactory);
        migrator.runMigrations();

        // 3. Создаём JDBI и репозиторий
        var jdbi = Jdbi.create(connectionFactory.getDataSource());
        StudentRepository repo = new StudentRepositoryImpl(jdbi);

        // 4. Демонстрируем все методы
        demoAllMethods(repo);

        System.out.println("Завершение работы");
    }

    private static void demoAllMethods(StudentRepository repo) {

        // READ — все студенты
        List<Student> students = repo.findAll();
        for (Student student : students) {
            System.out.println(student);
        }

        // CREATE — сохраняем студента
        Student student1 = new Student(0, "Иван Иванов", 4.2);
        int savedId = repo.save(student1);
        Student student2 = new Student(0, "Егор Иванов", 3.2);
        Student student3 = new Student(0, "Петя Иванов", 2.2);
        repo.save(student2);
        repo.save(student3);

        // READ — находим по ID
        Student found = repo.findById(savedId);
        System.out.println("Найден: " + found);


        // READ — все студенты
        List<Student> students1 = repo.findAll();
        for (Student student : students1) {
            System.out.println(student);
        }

        // UPDATE
        if (found != null) {
            found.setGrade(5.0);
            boolean updated = repo.update(found);
            System.out.println("✏Обновлён: " + updated);
    }

        Student found1 = repo.findById(savedId);
        System.out.println("Найден: " + found1);

        // DELETE — удаляем
        repo.deleteById(savedId);
        System.out.println("Удалён с ID = " + savedId);

        // Проверка
        System.out.println("После удаления: " + repo.findById(savedId));  // null

        List<Student> students2 = repo.findAll();

        for (Student student : students2) {
            System.out.println(student);
        }
    }


}
