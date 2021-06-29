package guru.sfg.brewery.config;

import guru.sfg.brewery.security.JpaUserDetailsService;
import guru.sfg.brewery.security.RestHeaderAuthFilter;
import guru.sfg.brewery.security.RestUrlAuthFilter;
import guru.sfg.brewery.security.SfgPasswordEncoderFactories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager) {
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    public RestUrlAuthFilter restUrlAuthFilter(AuthenticationManager authenticationManager) {
        RestUrlAuthFilter filter = new RestUrlAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return SfgPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
//
//    @Autowired
//    JpaUserDetailsService jpaUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Add RestHeaderAuthFilter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(restHeaderAuthFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();

        http.addFilterBefore(restUrlAuthFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class);

        // Add authorization
        http
                .authorizeRequests(authorize -> {
                    authorize
                            .antMatchers("/h2-console/**").permitAll() // do not use in PRD
                            .antMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll()
                            .antMatchers(HttpMethod.GET, "/api/v1/beer/**").hasAnyRole("ADMIN", "USER", "CUSTOMER")
                            .mvcMatchers(HttpMethod.DELETE, "/api/v1/beer/**").hasRole("ADMIN")
                            .mvcMatchers(HttpMethod.GET, "/api/v1/beerUpc/{upc}").permitAll()
                            .mvcMatchers("/brewery/breweries").hasAnyRole("ADMIN", "CUSTOMER")
                            .mvcMatchers(HttpMethod.GET, "/brewery/api/v1/breweries").hasAnyRole("ADMIN", "CUSTOMER")
                            .mvcMatchers("/beers/find", "/beers/{beerId}").hasAnyRole("ADMIN", "USER", "CUSTOMER");
                })
                .authorizeRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin();
        http.httpBasic();

        // h2 console config
        http.headers().frameOptions().sameOrigin();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.jpaUserDetailsService)
//                .passwordEncoder(passwordEncoder());

//        auth.inMemoryAuthentication()
//                .withUser("spring")
//                .password("{bcrypt}$2a$10$HAmM2bh8dp6QtT4cslVTZO9S5v8nKwiMviGn05EdBp.OKtVFBJKcW")
//                .roles("ADMIN")
//                .and()
//                .withUser("user")
//                .password("{sha256}1bf715fc621a30be960fd0894bebee013c99f43161610910725656909a3d8a337edf0f7bd972d7e5")
//                .roles("ADMIN");
//
//        auth.inMemoryAuthentication()
//                .withUser("scott")
//                .password("{bcrypt}$2a$10$bSEZ9i5Q0q0t35P0MfOb..PTShE14QRnEIPoz1nu1ZloJi6ucNNpe")
//                .roles("CUSTOMER");
//    }

    //    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder() // TODO: fix later
//                .username("spring")
//                .password("guru")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder() // TODO: fix later
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

}
