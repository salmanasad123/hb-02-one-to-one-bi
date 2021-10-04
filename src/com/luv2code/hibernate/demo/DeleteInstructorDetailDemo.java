package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

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


            // we need to break the bi-directional link because we don't have cascadeType.REMOVE and this
            // will not work unless we break the link because we are only deleting instructor detail
            // and we are keeping the instructor
            instructorDetail.getInstructor().setInstructorDetail(null);

            // we are deleting instructor delete and this is also delete instructor because
            // we have set cascadeType = ALL so every operation applied on instructorDetail will
            // cascade to instructor and this will result in deletion of the instructor from database
            session.delete(instructorDetail);

            // commit the transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // close the session
            session.close(); // use this to resolve leak issue because our connection is still in memory
            sessionFactory.close();
        }
    }

}
