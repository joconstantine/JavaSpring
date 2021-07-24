package com.luv2code.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements
	ConstraintValidator<CourseCode, String>
{
	private String[] coursePrefix;

	@Override
	public void initialize(CourseCode theCourseCode) {
		this.coursePrefix = theCourseCode.value();
	}

	@Override
	public boolean isValid(String theCode,
			ConstraintValidatorContext theConstraintValidatorContext) {
		
		boolean result = false;
		
		if (theCode != null) {
			for (String prefix: this.coursePrefix) {
				result = theCode.startsWith(prefix);
				if (result) return true;
			}
		} else {
			return true;
		}
		
		return result;
	}

}
