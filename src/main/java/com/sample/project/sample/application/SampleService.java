package com.sample.project.sample.application;

import com.sample.project.sample.api.dto.SampleRequest.Create;
import com.sample.project.sample.api.dto.SampleResponse;

public interface SampleService {

	SampleResponse create(Create request);
}
