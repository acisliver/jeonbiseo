package com.example.demo.config;

import com.example.demo.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration //빈등록(Ioc관리)
@EnableWebSecurity //security 필터 등록. 활성화 되어있는 security에서 해당 설정을 이 클래스에서 한다. Controller에 가기 전에 이 클래스에 접근하여 필터링 하게 한다.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private PrincipalDetailService principalDetailService;

    //이 함수는 Userservice에서 사용됨
    @Bean //bean에 등록 함 으로서 spring이 관리 하게 한다. 즉, Ioc가 되어서 어디서든 사용 할 수 있게 한다.
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    //principalDetailService에서 찾은 해당 아이디의 비밀번호가 어떠한 해싱 방법으로 해싱 되었는지 찾고
    //같은 방법으로 입력한 비밀번호를 해싱하고 DB에 저장된 값과 비교
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/http/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/auth/loginProc") //security가 해당 주고로 요청오는 로그인을 가로채서 대신함
                .defaultSuccessUrl("/")
                .usernameParameter("userName"); //Princi~의 loadUserByUsername메소드 파라미터가 username이어야만 제대로된 값을 받는데, name에 맞춰 이것만 추가해주면 됨. https://dublin-java.tistory.com/31 참고.
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}