package com.sample.project.product.domain;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@NoArgsConstructor
@ToString(of = {"name","money","createDate"})
public class Product {

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "money")
	private Long money;

	@Column(name = "create_date")
	private LocalDate createDate = LocalDate.now();

	@OneToOne(mappedBy = "product", cascade = CascadeType.PERSIST)
	private ProductItem productItem;

	public Product(String name, Long money) {
		this.name = name;
		this.money = money;
	}

	public void addCnt(Long cnt) {
		this.productItem = new ProductItem(cnt);
	}
}
