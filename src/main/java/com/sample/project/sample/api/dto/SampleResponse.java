package com.sample.project.sample.api.dto;

import com.sample.project.sample.domain.Sample;
import lombok.Data;

@Data
public class SampleResponse {

	private Long id;
	private String name;
	private int age;

	public static SampleResponse of(Sample save) {
		return null;
	}
}
