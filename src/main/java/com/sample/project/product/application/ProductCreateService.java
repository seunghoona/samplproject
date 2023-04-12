package com.sample.project.product.application;

import com.sample.project.product.domain.Product;
import com.sample.project.product.domain.ProductData;
import com.sample.project.product.domain.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductCreateService {

	private final ProductRepo productRepo;
	private final ProductHistoryService productHistoryService;

	@Transactional
	public Product createProduct(String name) {
		Product product = new Product(name, 10_000L);
		Product saveProduct = productRepo.saveAndFlush(product);

		ProductData productData = ProductData.builder()
			.name(saveProduct.getName())
			.money(saveProduct.getMoney())
			.createDate(saveProduct.getCreateDate())
			.build();

		productHistoryService.createHistory(null, productData);
		return product;
	}


}
