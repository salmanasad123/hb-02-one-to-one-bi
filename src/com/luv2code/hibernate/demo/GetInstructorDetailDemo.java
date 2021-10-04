package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {

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
            // start a transaction
            session.beginTransaction();

            // get the instructor detail object by their id / primary key
            int instructorDetailId = 2;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, instructorDetailId);

            // print the instructor detail object
            System.out.println("Found Instructor Details: " + instructorDetail);

            // print the associated instructor by using instructor detail (using bi-directional mapping)
            System.out.println("The associated Instructor : " + instructorDetail.getInstructor());

            // commit the transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

}
