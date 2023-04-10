package com.sample.project.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHistRepo extends JpaRepository<ProductHist, Long> {
}
