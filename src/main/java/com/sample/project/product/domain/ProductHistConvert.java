package com.sample.project.product.domain;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductHistConvert implements AttributeConverter<ProductData, String> {


	private final ObjectMapper objectMapper;

	@Override
	public String convertToDatabaseColumn(ProductData productData) {
		try {
			return objectMapper.writeValueAsString(productData);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ProductData convertToEntityAttribute(String dbData) {
		try {
			return objectMapper.readValue(dbData, ProductData.class);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
