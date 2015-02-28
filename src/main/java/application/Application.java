package application;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
			http.authorizeRequests().antMatchers("/admin/**")
					.fullyAuthenticated().and().formLogin()
					.defaultSuccessUrl("/home").usernameParameter("name")
					.passwordParameter("password").loginPage("/login")
					.failureUrl("/login?error").permitAll();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.userDetailsService(service);
		}
	}

	@Bean
	public DefaultKaptcha captchaProducer() {
		DefaultKaptcha kaptcha = new DefaultKaptcha();
		Properties properties=new Properties();
		properties.put("kaptcha.textproducer.char.string", "0123456789");
		properties.put("kaptcha.textproducer.char.length", "4");
		Config config=new Config(properties);
		kaptcha.setConfig(config);
		return kaptcha;
	}
}
