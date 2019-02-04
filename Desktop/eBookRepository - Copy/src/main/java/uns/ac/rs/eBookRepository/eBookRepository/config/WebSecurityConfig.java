package uns.ac.rs.eBookRepository.eBookRepository.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import uns.ac.rs.eBookRepository.eBookRepository.token.RestAuthenticationEntryPoint;
import uns.ac.rs.eBookRepository.eBookRepository.token.TokenAuthenticationFilter;
import uns.ac.rs.eBookRepository.eBookRepository.token.TokenHelper;
import uns.ac.rs.eBookRepository.eBookRepository.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
    //BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;

    //Neautorizovani pristup zastcenim resursima
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //Definisemo nacin autentifikacije
    //Svaki
    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( jwtUserDetailsService )
                .passwordEncoder( passwordEncoder() );
    }

    @Autowired
    TokenHelper tokenHelper;


    //Definisemo prava pristupa odredjenim URL-ovima
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //komunikacija izmedju klijenta i servera je stateless
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
                //za neautorizovane zahteve posalji 401 gresku
                .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
                .authorizeRequests()
                //svim korisnicima dopusti da pristupe putanjama /auth/**
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/users/{id}").permitAll()
                .antMatchers("/api/users/add").permitAll()
                .antMatchers("/api/users/update/{id}").permitAll()
                .antMatchers("/api/ebooks/**").permitAll()
                .antMatchers("/api/category/**").permitAll()
                .antMatchers("/api/languages/**").permitAll()
                .antMatchers("/api/**").permitAll() 
//                .antMatchers("/reindex/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/index/add").permitAll()
                .antMatchers("/api/demo/**").permitAll()
                //.antMatchers("/api/users").permitAll()
                //.antMatchers("/api/users/**").permitAll()
                //svaki zahtev mora biti autorizovan
                .anyRequest().authenticated().and()
                //presretni svaki zahtev filterom
                .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, jwtUserDetailsService), BasicAuthenticationFilter.class)

                .csrf().disable();
    }


    //Generalna bezbednost aplikacije
    @Override
    public void configure(WebSecurity web) throws Exception {
        // TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "api/auth/login",
                "/webjars/**",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        );
        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/",
                "/webjars/**",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        );
    }

}

