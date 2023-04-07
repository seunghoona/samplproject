package com.sample.project.sample.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import com.sample.project.exception.BusinessException;
import com.sample.project.sample.api.dto.SampleSearch;
import com.sample.project.sample.domain.Sample;
import com.sample.project.sample.domain.SampleRepo;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <h1>  샘플코드 DB </h1>
 * 사용하지 않을 예정
 */
@SpringBootTest
@Deprecated
class QSampleRepoImplTest {

	@Autowired
	private QSampleRepo qSampleRepo;

	@Autowired
	private SampleRepo sampleRepo;

	Sample 박상혁;
	Sample 나승후;

	@BeforeEach
	void setUp() {

		박상혁 = Sample.builder()
			.id(1L)
			.name("박상혁")
			.age(33)
			.build();

		나승후 = Sample.builder()
			.id(2L)
			.name("나승후")
			.age(33)
			.build();

		sampleRepo.saveAll(List.of(박상혁, 나승후));
	}

	@Test
	void 회원을_조회한다() {

		// given

		// when
		final var sample = qSampleRepo.findSample(SampleSearch.builder().build());

		// then
		assertThat(sample.size()).isEqualTo(2);
	}

	@Test
	void 기존_나이에_한살을_더한다() {

		// given
		final var sample = sampleRepo.findById(1L)
			.orElseThrow(BusinessException::new);

		// when
		sample.increment(1);


		// then
		assertThat(sample.getAge()).isEqualTo(34);

	}
}