package com.sample.project.sample.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SampleSearch {

	private String name;

	private String age;

}
