package com.company.hibernate.demo;

import com.company.hibernate.entity.Course;
import com.company.hibernate.entity.Instructor;
import com.company.hibernate.entity.InstructorDetail;
import com.company.hibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml") // its can be empty, search for default name
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .addAnnotatedClass(Review.class)
                                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            Course course = new Course("Pacman - How To Score One Million Points");

            course.addReviews(new Review("Great course ... loved it!"));
            course.addReviews(new Review("Cool course"));
            course.addReviews(new Review("What a dumb course"));

            System.out.println(course);
            System.out.println(course.getReviews());

            session.save(course);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!!");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
