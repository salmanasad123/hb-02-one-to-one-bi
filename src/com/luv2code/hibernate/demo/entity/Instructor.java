package com.luv2code.hibernate.demo.entity;

import javax.persistence.*;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // set up the relationship /mapping between instructor detail and the instructor
    // @OneToOne is used for 1-1 relation
    // cascade Type is set to ALL so all operations applied on instructor will cascade to instuctor detail like
    // delete, fetch, persisting (is instructor is saved, instructor detail will also be saved)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id") // this is the column to join on
    private InstructorDetail instructorDetail;

    public Instructor() {
    }

    // we removed instructor detail we want to manually pass it in not via constructor
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetailId=" + instructorDetail +
                '}';
    }
}
