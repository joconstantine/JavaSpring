package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// get the instructor from db
			// Hibernate Query with HQL
			int theId = 1;
			Query<Instructor> query = session.createQuery(
					"select i from Instructor i" + " JOIN FETCH i.courses " + " where i.id=:theInstructorId",
					Instructor.class);

			// get courses for the instructor
			query.setParameter("theInstructorId", theId);
			Instructor tempInstructor = query.getSingleResult();

			// associate the objects
			System.out.println("luv2code: Instructor: " + tempInstructor);

			// execute the query and get instructor
			tempInstructor = query.getSingleResult();

			// commit transaction
			session.getTransaction().commit();

			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());

			System.out.println("luv2code: Done!");
		} finally {

			// add clean up code
			session.close();

			factory.close();
		}

	}

}
