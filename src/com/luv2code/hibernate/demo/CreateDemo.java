package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {


        // this time we add our both classes (Instructor & InstructorDetail) to annotated classes
        // so hibernate can read those classes and load them to make them available
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create the session
        Session session = sessionFactory.getCurrentSession();

        try {

            // create the objects
            Instructor instructor = new Instructor("John", "Doe", "john@luv2code.com");
            InstructorDetail instructorDetail =
                    new InstructorDetail("www.youtube.com/luv2code", "Coding");

            // associate the objects, we need to hook our objects together
            instructor.setInstructorDetail(instructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor, we give the object we want to save and its instructor
            // this will also save the instructor detail object because of cascadeType.ALL
            // so it will save the object and any associated object, so save operation will cascade
            // to all associated objects
            session.save(instructor);

            // commit the transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

}
