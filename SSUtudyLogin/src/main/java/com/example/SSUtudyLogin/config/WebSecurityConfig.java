package com.example.SSUtudyLogin.config;

import com.example.SSUtudyLogin.filter.JwtAuthenticationFilter;
import com.example.SSUtudyLogin.repository.JwtTokenProvider;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.persistence.EntityManager;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { //스프링 시큐리티 필터 사용함을 알림


    @Lazy
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        //httpBasic auth : 사용자 id , passwd 를 http 헤더에 base64 인코딩 형태로 넣어서 인증 요청
        // id passwd 인코딩 -> Authorization 헤더로 서버에 전송 하여 인증 요청
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/users/login").permitAll()
                .antMatchers("/**").hasRole("USER")
//                .antMatchers("/**").permitAll() //토큰 없을 때 test용..사용할 때 위 3줄은 주석처리
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//        return super.authenticationManagerBean();
//    }





//    protected void configure(HttpSecurity http) throws Exception {
//
//    }

//    @Override
//    public void init(SecurityBuilder builder) throws Exception {
//
//    }
//
//    @Override
//    public void configure(SecurityBuilder builder) throws Exception {
//
//    }
}
