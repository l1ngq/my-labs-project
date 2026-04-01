package com.l1ngq.labs;

import com.l1ngq.labs.config.ConnectionFactory;
import com.l1ngq.labs.config.DatabaseMigrator;
import com.l1ngq.labs.entity.Student;
import com.l1ngq.labs.repository.*;
import com.l1ngq.labs.service.StudentNotFoundException;
import com.l1ngq.labs.service.StudentService;
import com.l1ngq.labs.service.StudentServiceImpl;
import org.jdbi.v3.core.Jdbi;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        // 1. Создаём пул соединений
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 2. Применяем миграции Liquibase
        DatabaseMigrator migrator = new DatabaseMigrator(connectionFactory);
        migrator.runMigrations();

        // 3. Создаём JDBI, репозиторий и сервис
        var jdbi = Jdbi.create(connectionFactory.getDataSource());
        StudentRepository repo = new StudentRepositoryImpl(jdbi);
        StudentService service = new StudentServiceImpl(repo);

        // 4. Демонстрируем все методы
        demoAllMethods(service);

        System.out.println("Завершение работы");
    }

    private static void demoAllMethods(StudentService service) {

        // READ — все студенты
        List<Student> students = service.findAll();
        for (Student student : students) {
            System.out.println(student);
        }

        // CREATE — сохраняем студента
        int savedId = service.save("Иван Иванов", 4.2);
        service.save("Егор Иванов", 3.2);
        service.save("Петя Иванов", 2.2);

        // READ — находим по ID
        Student found = service.findById(savedId);
        System.out.println("Найден: " + found);


        // READ — все студенты
        List<Student> students1 = service.findAll();
        for (Student student : students1) {
            System.out.println(student);
        }

        // UPDATE
        if (found != null) {
            found.setGrade(5.0);
            service.update(found);
            System.out.println("Обновлён");
        }

        Student found1 = service.findById(savedId);
        System.out.println("Найден: " + found1);

        // DELETE — удаляем
        service.deleteById(savedId);
        System.out.println("Удалён с ID = " + savedId);

        // Проверка
        try {
            System.out.println("После удаления: " + service.findById(savedId));
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }

        List<Student> students2 = service.findAll();

        for (Student student : students2) {
            System.out.println(student);
        }

        try {
            service.findById(999999);
        } catch (StudentNotFoundException e) {
            System.out.println("Ожидаемая ошибка findById: " + e.getMessage());
        }

        try {
            service.deleteById(999999);
        } catch (StudentNotFoundException e) {
            System.out.println("Ожидаемая ошибка deleteById: " + e.getMessage());
        }
    }


}
