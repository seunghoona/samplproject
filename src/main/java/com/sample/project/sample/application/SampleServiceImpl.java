package com.sample.project.sample.application;

import com.sample.project.sample.api.dto.SampleRequest.Create;
import com.sample.project.sample.api.dto.SampleResponse;
import com.sample.project.sample.domain.Sample;
import com.sample.project.sample.domain.SampleRepo;
import com.sample.project.sample.infrastructure.QSampleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

	private final SampleRepo sampleRepo;
	private final QSampleRepo QsampleRepo;

	/**
	 * 
	 * @param request - 샘플저장
	 * @since 
	 */
	@Override
	public SampleResponse create(Create request) {

		Sample sample = Sample.builder()
			.name(request.getName())
			.age(request.getAge())
			.build();

		return SampleResponse.of(sampleRepo.save(sample));
	}
}
