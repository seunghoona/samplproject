package com.sample.project.sample.application;

import com.sample.project.sample.api.dto.SampleRequest;
import com.sample.project.sample.api.dto.SampleRequest.Create;
import com.sample.project.sample.api.dto.SampleResponse;
import com.sample.project.sample.api.dto.SampleSearch;
import java.util.List;
import javax.validation.constraints.NotNull;

public interface SampleService {


	List<SampleResponse> getSamples(SampleSearch sampleSearch);

	SampleResponse saveSample(Create request);

	SampleResponse modifySample(
		@NotNull Long id,
		@NotNull SampleRequest.Modify request);

	void removeSample(
		@NotNull List<Long> ids);
}
