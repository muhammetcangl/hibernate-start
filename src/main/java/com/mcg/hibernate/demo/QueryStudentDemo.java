package com.mcg.hibernate.demo;

import com.mcg.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {

            // create a student object
            session.beginTransaction();

            //query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            //display the students
            displayStudents(theStudents);

            // query students : lastName = 'Duck'
            theStudents = session.createQuery("from Student s where s.lastName='Duck'").getResultList();

            // display the students
            System.out.println("Students who have last name of Duck");
            displayStudents(theStudents);

            // query students lastName = 'Duck OR firstName = 'Muhammetcan'
            theStudents = session.createQuery("from Student s where"
                                    + " s.lastName='Duck' OR s.firstName='Muhammetcan'").getResultList();

            // display the students
            System.out.println("Students who have last name of Duck OR first name Muhammetcan");
            displayStudents(theStudents);

            // query students where email like '%gmail.com'
            theStudents = session.createQuery("from Student s where"
                                    + " s.email LIKE '%gmail.com'").getResultList();
            System.out.println("Students whose email ends with 'gmail.com'");
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }
}
