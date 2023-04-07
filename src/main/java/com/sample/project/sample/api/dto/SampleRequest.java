package com.sample.project.sample.api.dto;

import static lombok.AccessLevel.PROTECTED;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
public class SampleRequest {

	@Data
	public static class Create {

		private String name;
		private int age;

	}

	@Data
	public static class Update {

		private String name;
		private int age;
	}

}
