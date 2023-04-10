package com.sample.project.product.application;

import com.sample.project.product.domain.Product;
import com.sample.project.product.domain.ProductData;
import com.sample.project.product.domain.ProductHist;
import com.sample.project.product.domain.ProductHistRepo;
import com.seunghoona.transaction.concurrency.product.domain.Product;
import com.seunghoona.transaction.concurrency.product.domain.ProductData;
import com.seunghoona.transaction.concurrency.product.domain.ProductHist;
import com.seunghoona.transaction.concurrency.product.domain.ProductHistRepo;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductHistoryService {

	private final ProductHistRepo productHistRepo;

	@Transactional(propagation = Propagation.NESTED)
	public void createHistory(Product product, ProductData productData) {
		ProductHist save = productHistRepo.save(new ProductHist(product, productData));
		throw new EntityNotFoundException();
	}
}
