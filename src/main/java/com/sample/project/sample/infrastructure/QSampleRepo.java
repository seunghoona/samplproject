package com.sample.project.sample.infrastructure;

import com.sample.project.sample.api.dto.SampleResponse;
import com.sample.project.sample.api.dto.SampleSearch;
import java.util.List;

/**
 * <h1> SAMPLE QUERY DSL </h1>
 *
 * @author seunghoona <br/>
 * <p> 화면에 맞는 데이터를 조회할 때 사용한다. </p>
 *
 */
public interface QSampleRepo {

	List<SampleResponse> findSample(SampleSearch sampleSearches);
}
