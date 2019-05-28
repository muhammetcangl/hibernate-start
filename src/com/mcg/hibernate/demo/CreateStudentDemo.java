package com.mcg.hibernate.demo;

import com.mcg.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.StreamUtils;

public class CreateStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            // use the session object to save Java object
            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Muhammetcan" , "Gül", "muhammetcangl@gmail.com");

            // create a student object
            session.beginTransaction();

            // start a transaction
            System.out.println("Saving the student...");
            session.save(tempStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
