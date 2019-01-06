package ru.matevosyan.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import javax.sql.DataSource;

/**
 * WebSecurityCustomConfig is application security config class.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityCustomConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    /**
     * Constructor.
     * @param dataSource app dataSource.
     */
    @Autowired
    public WebSecurityCustomConfig(@Qualifier("dataSource") final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Config for checking authentication in the database.
     * @param auth is AuthenticationManagerBuilder.
     * @throws Exception exception.
     */
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String usersByUsername = "select name, password, enabled from users where name=?";
        String authoritiesByUsername = "select u.name, r.name from roles as r inner join users as u  on u.fk_role_id=r.id where u.name=?";
        auth
                .jdbcAuthentication()
                .dataSource(this.dataSource)
                .usersByUsernameQuery(usersByUsername)
                .authoritiesByUsernameQuery(authoritiesByUsername)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * crypt user password with bCryptPasswordEncoder() method.
     * @return BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Handle denied request.
     * @return AuthAccessDeniedHandler.
     */
    @Bean
    public AccessDeniedHandler authAccessDeniedHandler() {
        return new AuthAccessDeniedHandler();
    }

    /**
     * Configure user requests, login form, sign out request, access denied handler and csrf.
     * @param http is HttpSecurity object.
     * @throws Exception is exception to throw.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/signIn")
                        .access("isAnonymous()")
                    .antMatchers("/**/offer", "/**/uploadFile", "/**/offerSellStatusValue", "/signOut")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/signUp", "/signOut", "/images/**", "/css/**", "/js/**", "/resources/**", "/", "/**/allOffers",
                            "/**/lastAddedOffers", "/**/withPhoto", "/**/withBrands", "/**/withBrands?*")
                        .permitAll()
                .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .failureUrl("/signIn?error")
                    .loginProcessingUrl("/signIn")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .logout()
                        .logoutUrl("/signOut")
                        .logoutSuccessUrl("/signIn")
                        .invalidateHttpSession(true)


                .and()
                    .exceptionHandling().accessDeniedHandler(authAccessDeniedHandler())

                .and()
                    .csrf().disable();
    }
}
