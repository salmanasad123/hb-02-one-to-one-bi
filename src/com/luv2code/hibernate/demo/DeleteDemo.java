package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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

            // get the instructor by their id / primary key
            int instructorId = 1;
            Instructor instructor = session.get(Instructor.class, instructorId);
            System.out.println("Found Instructor : " + instructor);

            // delete the instructor, we give the object we want to delete and its instructor
            // this will also delete the instructor detail object because of cascadeType.ALL
            // so it will delete the object and any associated object, so delete operation will cascade
            // to all associated objects
            if (instructor != null) {
                System.out.println("Deleting: " + instructor);
                session.delete(instructor);

            }
            // commit the transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

}
