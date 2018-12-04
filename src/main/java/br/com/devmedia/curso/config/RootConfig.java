package br.com.devmedia.curso.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan("br.com.devmedia.curso")
@Configuration
public class RootConfig {

}
