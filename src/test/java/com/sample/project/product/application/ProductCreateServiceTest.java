package com.sample.project.product.application;

import com.sample.project.product.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProductCreateServiceTest {


	@Autowired
	private ProductCreateService createService;

	@Test
	void test() {
		Product 상품 = createService.createProduct("상품");
		System.out.println("상품 = " + 상품);
	}

}