package com.sample.project.sample.infrastructure;

import static com.sample.project.sample.domain.QSample.*;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.project.sample.api.dto.SampleResponse;
import com.sample.project.sample.api.dto.SampleSearch;
import com.sample.project.sample.domain.QSample;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QSampleRepoImpl implements QSampleRepo {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<SampleResponse> findSample(SampleSearch sampleSearches) {
		return jpaQueryFactory
			.select(Projections.bean(
				SampleResponse.class,
				sample.id,
				sample.name,
				sample.age
			))
			.from(sample)
			.fetch();
	}
}
