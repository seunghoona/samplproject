package com.sample.project.current;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.sample.project.ProjectApplication;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class ConcurrentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@PersistenceContext
	EntityManager entityManager;


	@BeforeEach
	void beforeEach() {
		ProjectApplication.main(new String[]{"8080"});
		ProjectApplication.main(new String[]{"9090"});
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void test() throws Exception {

		this.mockMvc.perform(
				get("http://localhost:8080/order/1"))
			.andDo(print());

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		Runnable 지연조회 = () -> {

			try {
				Thread.sleep(5000);
				log.debug("ThreadName [8080]:::" + Thread.currentThread().getName());
				String contentAsString = this.mockMvc.perform(get("http://localhost:8080/order/1"))
					.andReturn()
					.getResponse()
					.getContentAsString();
				log.debug("ThreadName [8080]:::" + contentAsString);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		Runnable 지연조회중_저장 = () -> {

			try {
				log.debug("ThreadName [9090] :::" + Thread.currentThread().getName());
				String contentAsString = this.mockMvc.perform(post("http://localhost:9090/order/1")
						.content("{\"name\":\"testUserDetails\"}"))
					.andReturn()
					.getResponse()
					.getContentAsString();
				log.debug("ThreadName [9090]:::" + contentAsString);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		Runnable 이때_마지막_8080서버의_조회상태 = () -> {

			try {
				log.debug("ThreadName [8080] :::" + Thread.currentThread().getName());
				String contentAsString = this.mockMvc.perform(get("http://localhost:8080/order/1")
					).andReturn()
					.getResponse()
					.getContentAsString();
				log.debug("ThreadName [8080]:::" + contentAsString);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		executorService.submit(지연조회);
		executorService.submit(지연조회중_저장);
		executorService.submit(이때_마지막_8080서버의_조회상태);

		// 예상열과 값
		// => testUserDeatils

		Thread.sleep(100000);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void 서로다른서버에서_DB데이터를변경한경우_데이터는_해당서버의_1차캐시를_바라보게된다() throws Exception {

		this.mockMvc.perform(
				get("http://localhost:8080/order/1"))
			.andDo(print());

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		Runnable 서버8080에서_저장 = () -> {

			try {
				log.debug("ThreadName [8080] :::" + Thread.currentThread().getName());
				String contentAsString = this.mockMvc.perform(post("http://localhost:8080/order/1")
						.content("{\"name\":\"test1\"}"))
					.andReturn()
					.getResponse()
					.getContentAsString();
				log.debug("ThreadName [8080]:::" + contentAsString);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		Runnable 서버9090에서_저장 = () -> {

			try {
				log.debug("ThreadName [9090] :::" + Thread.currentThread().getName());
				String contentAsString = this.mockMvc.perform(post("http://localhost:9090/order/1")
						.content("{\"name\":\"test2\"}"))
					.andReturn()
					.getResponse()
					.getContentAsString();
				log.debug("ThreadName [9090]:::" + contentAsString);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		Runnable 이때_마지막_8080서버의_조회상태 = () -> {

			try {
				log.debug("ThreadName 마지막조회 [8080] :::" + Thread.currentThread().getName());
				String contentAsString = this.mockMvc.perform(get("http://localhost:8080/order/1")
					).andReturn()
					.getResponse()
					.getContentAsString();
				log.debug("ThreadName 마지막조회 결과 [8080]:::" + contentAsString);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		executorService.submit(서버8080에서_저장);
		executorService.submit(서버9090에서_저장);
		executorService.submit(이때_마지막_8080서버의_조회상태);

		// 예상열과 값
		// => testUserDeatils

		Thread.sleep(100000);
	}


	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void 서로다른서버에서_DB데이터를변경할때_VESION을_추가하면_서로다른_엔티티의_값이더라도_값을보장해준다() throws Exception {

		this.mockMvc.perform(
				get("http://localhost:8080/order/1"))
			.andDo(print());

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		Runnable 서버8080에서_저장 = () -> {

			try {
				log.debug("ThreadName [8080] :::" + Thread.currentThread().getName());
				String contentAsString = this.mockMvc.perform(post("http://localhost:8080/order/1")
						.content("{\"name\":\"test1\"}"))
					.andReturn()
					.getResponse()
					.getContentAsString();
				log.debug("ThreadName [8080]:::" + contentAsString);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		Runnable 서버9090에서_저장 = () -> {

			try {
				log.debug("ThreadName [9090] :::" + Thread.currentThread().getName());
				String contentAsString = this.mockMvc.perform(post("http://localhost:9090/order/1")
						.content("{\"name\":\"test2\"}"))
					.andReturn()
					.getResponse()
					.getContentAsString();
				log.debug("ThreadName [9090]:::" + contentAsString);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		Runnable 이때_마지막_8080서버의_조회상태 = () -> {

			try {
				log.debug("ThreadName 마지막조회 [8080] :::" + Thread.currentThread().getName());
				String contentAsString = this.mockMvc.perform(get("http://localhost:8080/order/1")
					).andReturn()
					.getResponse()
					.getContentAsString();
				log.debug("ThreadName 마지막조회 결과 [8080]:::" + contentAsString);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		executorService.submit(서버8080에서_저장);
		executorService.submit(서버9090에서_저장);
		executorService.submit(이때_마지막_8080서버의_조회상태);

		// 예상열과 값
		// => testUserDeatils

		Thread.sleep(100000);
	}
}