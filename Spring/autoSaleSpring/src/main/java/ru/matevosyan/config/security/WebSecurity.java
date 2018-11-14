package ru.matevosyan.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public WebSecurity(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String usersByUsername = "select name, password, enabled from users where name = ?";
        String authoritiesByUsername = "select u.name, r.name from users as u,roles as r where u.fk_role_id=r.id and u.name = ?";

        auth
                .jdbcAuthentication()
                .dataSource(this.dataSource)
                .usersByUsernameQuery(usersByUsername)
                .authoritiesByUsernameQuery(authoritiesByUsername);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //offerSellStatusValue for owner or admin
        http
                .authorizeRequests()
                    .antMatchers("/images/**", "/**/signUp", "/", "/signIn/**")
                        .permitAll()
                //all mapping from ajax as "./allOffers", "./offer", "./lastAddedOffers", "./withPhoto", "./withBrands"
                    .antMatchers("/allOffers","/offer", "/lastAddedOffers", "/withPhoto", "/withBrands")
                        .access("hasRole('admin') and hasRole('user')")
                .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .loginPage("/signIn")
                .and()
                    .logout()
                        .logoutUrl("/signOut")
                        .logoutSuccessUrl("/signIn")
                        .invalidateHttpSession(true)
                .and()
                    .csrf().disable();
    }
}
