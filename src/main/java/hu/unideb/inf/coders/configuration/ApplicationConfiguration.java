package hu.unideb.inf.coders.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("hu.unideb.inf.coders")
public class ApplicationConfiguration {

	// Bean for accessing properties.

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// Bean for JSON conversion.

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	// Bean for conversion between entities and dtos

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
