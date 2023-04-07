package com.sample.project.sample.api;

import com.sample.project.sample.application.SampleService;
import com.sample.project.sample.api.dto.SampleRequest.Create;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * <h1> 샘플용 코드 </h1>
 * @author seunghoona
 */
@RestController("/api/samples")
@RequiredArgsConstructor
public class SampleController {

	private final SampleService sampleService;


	@GetMapping
	public ResponseEntity<List<String>> getSample() {
		return ResponseEntity.ok(List.of("1", "2"));
	}

	@PostMapping("/{id}")
	public ResponseEntity<List<String>> createSample(
		@PathVariable String id,
		@RequestBody Create request) {

		sampleService.create(request);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(id)
			.toUri();

		return ResponseEntity.created(location).build();
	}

	@PatchMapping
	public ResponseEntity<List<String>> editingSample() {
		return ResponseEntity.ok(List.of("1", "2"));
	}

	@DeleteMapping
	public ResponseEntity<List<String>> removeSample() {
		return ResponseEntity.noContent().build();
	}

}
