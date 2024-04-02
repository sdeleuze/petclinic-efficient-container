package org.springframework.samples.petclinic.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.PostgresDialect;

@Configuration
@Profile("default")
public class DataConfiguration {

	@Bean
	Dialect dialect() {
		return PostgresDialect.INSTANCE;
	}
}
