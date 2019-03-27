package com.beans;

import org.apache.camel.Header;

public class ValidatorBean {
	public boolean validateGender(@Header("gender") String gender) {
		if (gender.equals("Male")) {
			return true;
		} else {
			return false;
		}
	}
}
