package application;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import application.converter.StringToContent;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Resource(name = "authService")
		private UserDetailsService service;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/center/**").hasRole("USER").anyRequest()
					.permitAll().and().formLogin().defaultSuccessUrl("/home")
					.usernameParameter("name").passwordParameter("password")
					.loginPage("/login").failureUrl("/login?error").permitAll();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.userDetailsService(service).passwordEncoder(passwordEncoder());
		}

		@Bean
		public ShaPasswordEncoder passwordEncoder() throws Exception {
			return new ShaPasswordEncoder(256);
		}
	}

	@Bean
	public DefaultKaptcha captchaProducer() {
		DefaultKaptcha kaptcha = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.put("kaptcha.textproducer.char.string", "0123456789");
		properties.put("kaptcha.textproducer.char.length", "4");
		Config config = new Config(properties);
		kaptcha.setConfig(config);
		return kaptcha;
	}
	
	@Bean
	public ConversionServiceFactoryBean conversionService(){
		ConversionServiceFactoryBean factoryBean=new ConversionServiceFactoryBean();
		Set<Converter> converters=new HashSet<Converter>();
		converters.add(stringToContent());
		factoryBean.setConverters(converters);
		return factoryBean;
	}
	
	@Bean
	public StringToContent stringToContent(){
		return new StringToContent();
	}
}
