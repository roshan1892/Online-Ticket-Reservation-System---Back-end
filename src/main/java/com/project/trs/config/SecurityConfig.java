package com.project.trs.config;

import com.project.trs.filter.JwtAuthorizationFilter;
import com.project.trs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and()
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/createUser").permitAll()
            .antMatchers("/error").permitAll()
            .antMatchers("/unauthorized").permitAll()
            .antMatchers("/404").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutRequestMatcher(
                    new AntPathRequestMatcher("/login?logout"))
            .logoutSuccessUrl("/").permitAll()
            .and().csrf().disable()
            .addFilter(new JwtAuthorizationFilter(authenticationManager()))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.headers().frameOptions().sameOrigin();
  }

  @Bean
  public RequestDataValueProcessor requestDataValueProcessor() {
    return new CsrfRequestDataValueProcessor();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication()
            .withUser("bh")
            .password(passwordEncoder.encode("vghv"))
            .authorities("hgh");

    auth.userDetailsService(userService);
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.applyPermitDefaultValues();
    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
}
