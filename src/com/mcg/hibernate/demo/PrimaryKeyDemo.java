package com.mcg.hibernate.demo;

import com.mcg.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;

public class PrimaryKeyDemo {

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
            System.out.println("Creating new student objects...");

            Student tempStudent1 = new Student("Muhammetcan" , "deneme", "muhammetcangl@gmail.com",DateUtils.parseDate("01/01/2000"));
            Student tempStudent2 = new Student("Nazlican" , "deneme", "nazlican@gmail.com",DateUtils.parseDate("01/01/2000"));
            Student tempStudent3 = new Student("Yarencan" , "deneme", "yarencan@gmail.com",DateUtils.parseDate("01/01/2000"));

            // create a student object
            session.beginTransaction();

            // start a transaction
            System.out.println("Saving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
