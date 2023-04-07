package com.sample.project.sample.application;

import com.sample.project.exception.BusinessException;
import com.sample.project.sample.api.dto.SampleRequest;
import com.sample.project.sample.api.dto.SampleRequest.Create;
import com.sample.project.sample.api.dto.SampleResponse;
import com.sample.project.sample.api.dto.SampleSearch;
import com.sample.project.sample.domain.Sample;
import com.sample.project.sample.domain.SampleRepo;
import com.sample.project.sample.infrastructure.QSampleRepo;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

	private final SampleRepo sampleRepo;
	private final QSampleRepo qsampleRepo;

	@Override
	public List<SampleResponse> getSamples(@NotNull SampleSearch sampleSearch) {
		return qsampleRepo.findSample(sampleSearch);
	}

	/**
	 * 
	 * @param request - 샘플저장
	 * @since 
	 */
	@Override
	public SampleResponse saveSample(@NotNull Create request) {

		final var saveSample = Sample.builder()
			.name(request.getName())
			.age(request.getAge())
			.build();

		return SampleResponse.of(sampleRepo.save(saveSample));
	}

	@Override
	public SampleResponse modifySample(
		@NotNull Long id,
		@NotNull SampleRequest.Modify request) {

		final var findSample = sampleRepo.findById(id)
			.orElseThrow(BusinessException::new);

		return SampleResponse.of(sampleRepo.save(findSample));
	}

	@Override
	public void removeSample(
		@NotNull List<Long> ids) {
		List<Sample> findSamples = sampleRepo.findAllById(ids);
		sampleRepo.deleteAll(findSamples);
	}
}
