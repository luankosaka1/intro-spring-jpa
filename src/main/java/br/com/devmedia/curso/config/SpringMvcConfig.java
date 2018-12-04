package br.com.devmedia.curso.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.com.devmedia.curso.conversor.TipoSexoConverter;

@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver rs = new InternalResourceViewResolver();
		rs.setPrefix("/WEB-INF/views/");
		rs.setSuffix(".jsp");
		rs.setViewClass(JstlView.class);
		return rs;
	}
	
	@Bean
	public MessageSource source() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages");
		return source;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/resources/bootstrap/");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new TipoSexoConverter());
	}
}
