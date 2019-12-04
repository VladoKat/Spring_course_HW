package com.example.hw1.config;

import com.example.hw1.exception.EntityNotFoundException;
import com.example.hw1.security.RestAuthenticationEntryPoint;
import com.example.hw1.security.RestSavedRequestAwareAuthenticationSuccessHandler;
import com.example.hw1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private RestSavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/**").authenticated()
//                .antMatchers(HttpMethod.POST, "/**").hasAnyRole("BLOGGER", "ADMIN")
//                .antMatchers(HttpMethod.PUT).hasAnyRole("BLOGGER", "ADMIN")
//                .antMatchers(HttpMethod.DELETE).hasAnyRole("BLOGGER", "ADMIN")
//                .and()
//                .formLogin()
//                .permitAll()
//                .successHandler(authenticationSuccessHandler)
//                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
//                .and()
//                .logout()
//                .deleteCookies("JSESSIONID")
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .logoutUrl("/logout");
////                .and()
////                    .rememberMe();

        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {

        return username -> {
            try {
                return userService.findByUsername(username);
            } catch (EntityNotFoundException ex) {
                throw new UsernameNotFoundException(ex.getMessage(), ex);
            }
        };

    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder()
//                .username("user").password("user")
//                .roles("USER").build());
//        return manager;
//    }

}