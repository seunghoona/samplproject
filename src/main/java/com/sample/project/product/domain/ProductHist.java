package com.sample.project.product.domain;


import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class ProductHist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hist_no")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", updatable = false)
	private Product product;

	@Convert(converter = ProductHistConvert.class)
	@Column(name = "product_data", updatable = false)
	private ProductData productData;

	public ProductHist(Product product, ProductData productData) {
		this.product = product;
		this.productData = productData;
	}
}
