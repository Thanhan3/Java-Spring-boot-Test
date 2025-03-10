package com.javaweb.model;

import java.util.List;

public class ErrorResponseDTO {

	private String error;
	private List<String> details;

	public String getError() {
		return error;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}
