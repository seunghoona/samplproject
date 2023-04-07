package com.sample.project.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor.TextPredictor;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 *  <h1> JPA Configuration </h1>
 * @author seunghoona
 *
 */
@Configuration
public class JPAConfiguration {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @author seunghoona
	 */
	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
}
