package com.in28minutes.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.DeleteCourseDetailsRequest;
import com.in28minutes.courses.DeleteCourseDetailsResponse;
import com.in28minutes.courses.GetAllCourseDetailsRequest;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.in28minutes.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.in28minutes.soap.webservices.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.in28minutes.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import com.in28minutes.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailsEnpoint {

	@Autowired
	CourseDetailsService courseDetailsService;

	@PayloadRoot(namespace = "http://in28Minutes.com/courses",
			localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest
	(@RequestPayload GetCourseDetailsRequest request) {		
		Course course = courseDetailsService.findById(request.getId());

		if (course == null) {
			throw new CourseNotFoundException("Invalid Course Id " + request.getId());
		}

		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		CourseDetails courseDetails = mapCourse(course);
		response.setCourseDetails(courseDetails);

		return response;
	}

	@SoapAction("GetAllCourseDetails")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest
	(@RequestPayload GetAllCourseDetailsRequest request) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		List<Course> courses = courseDetailsService.findAll();

		for (Course course: courses) {
			CourseDetails courseDetails = mapCourse(course);
			response.getCourseDetails().add(courseDetails);
		}

		return response;
	}

	@PayloadRoot(namespace = "http://in28Minutes.com/courses",
			localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest
	(@RequestPayload DeleteCourseDetailsRequest request) {		
		Status status = courseDetailsService.deleteById(request.getId());

		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));

		return response;
	}

	private com.in28minutes.courses.Status mapStatus(Status status) {
		if (status == Status.FAILURE)
			return com.in28minutes.courses.Status.FAILURE;

		return com.in28minutes.courses.Status.SUCCESS;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());

		return courseDetails;
	}

}
