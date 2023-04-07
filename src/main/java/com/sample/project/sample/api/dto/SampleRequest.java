package com.sample.project.sample.api.dto;

import static lombok.AccessLevel.PROTECTED;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor(access = PROTECTED)
public class SampleRequest {

	@Data
	@Builder
	@AllArgsConstructor
	public static class Create {

		@NotNull(message = "")
		private String name;

		@Length(min = 1, max = 10, message = "{0} 이상 {1} 이하")
		private int age;

	}

	@Data
	@Builder
	@AllArgsConstructor
	public static class Modify {

		@NotNull(message = "")
		private String name;

		@Length(min = 1, max = 10, message = "{0} 이상 {1} 이하")
		private int age;
	}

}
