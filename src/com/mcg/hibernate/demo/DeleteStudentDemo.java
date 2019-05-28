package com.mcg.hibernate.demo;

import com.mcg.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;

            // find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + studentId);

            // create a student object
            session.beginTransaction();

            Student myStudent = session.get(Student.class, studentId);

            // delete the student
            //System.out.println("Deleting student: " + myStudent);
            //session.delete(myStudent);

            // delete student id=2
            System.out.println("Deleting student id=2");
            session.createQuery("delete from Student where id=2").executeUpdate();

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }
}
