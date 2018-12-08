package ru.matevosyan.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityCustomConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public WebSecurityCustomConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() { return new BCryptPasswordEncoder();}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/**/signUp", "/signOut", "/signIn")
                        .anonymous()
                    .antMatchers( "/images/**", "/", "/**/allOffers", "/**/lastAddedOffers"
                            , "/**/withPhoto", "/**/withBrands")
                        .permitAll()
                    .antMatchers(  "/**/offer", "/**/uploadFile", "/**/offerSellStatusValue")
                        .hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .loginProcessingUrl("/signIn")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .logout()
                        .logoutUrl("/signOut")
                        .logoutSuccessUrl("/signIn")
                        .invalidateHttpSession(true)
                    .permitAll()

                .and()
                    .csrf().disable();
    }
}
