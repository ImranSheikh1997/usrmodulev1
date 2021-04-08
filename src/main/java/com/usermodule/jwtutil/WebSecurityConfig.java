//This is Servlet Filter Like Config.
//Filter Comes Before Authentication. it will Filter out requests before it goes to Authentication.

package com.usermodule.jwtutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    MyUserDetails myUserDetails;

//    @Autowired
//    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// configure AuthenticationManager so that it knows from where to load
// user for matching credentials
// Use BCryptPasswordEncoder
        auth.userDetailsService(myUserDetails).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Disable CSRF(Cross Site Request Forgery)
        http.csrf().disable();
        http.cors().disable();
        http.headers().frameOptions().sameOrigin().and().authorizeRequests();
        //Entry Points (Allowing Requests)
        http.authorizeRequests()
                .antMatchers("/","/usermodule","/registration","/**").permitAll()
                .antMatchers("/","/usermodule","/registration","/checkemail","/**").permitAll()
                .antMatchers("/","/usermodule","/signin","/","**").permitAll()
                .antMatchers("/","/usermodule","/registration","/confirm/**").permitAll()
                .antMatchers("/","/registration","/country","/**").permitAll()
                .antMatchers("/","/registration","/state","/**").permitAll()
                .antMatchers("/","/registration","/city","/**").permitAll()
                .antMatchers("/","/v2","/api-docs").permitAll()
                .antMatchers("/","/swagger-resources/**").permitAll()
                .antMatchers("/","/swagger-ui.html").permitAll()
                .antMatchers("/","/configuration/**").permitAll()
                .antMatchers("/","/webjars/**").permitAll()
                .antMatchers("/","/public").permitAll()
                .antMatchers("/topic","/greetings").permitAll()
                .antMatchers("/hello").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/websocket","/blog","/**","/**","/websocket").permitAll()
                //Disallow everything else...
                .anyRequest().authenticated();

            http.formLogin()
                .loginPage("/signin")
                .permitAll();

       //No Session Will be Created or  used by Spring Security
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().

                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //If User try to access resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/login");

//        //Apply JWT
        //http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        //if you want to test api from browser

         //http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Allowing swagger to access without authentication
        web.ignoring().antMatchers("/v2/api-docs")//
                .antMatchers("/swagger-resources/**")//
                .antMatchers("/swagger-ui.html")//
                .antMatchers("/configuration/**")//
                .antMatchers("/webjars/**")//
                .antMatchers("/public")//
                .antMatchers("usermodule/registration/**")
                .antMatchers("usermodule/login")
                .antMatchers("usermodule/signin")
                .antMatchers("usermodule/registration/confirm")
                .antMatchers("/topic","/greetings")
                .antMatchers("/hello")
                .antMatchers("/websocket/","**")
                .antMatchers("/websocket","/blog","/**","/**","/websocket")
                .and()
                .ignoring();
    }

//    //TODO: for testing if it does not work then remove it
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetails);
//    }

    //To decrypt password with length 12
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}





