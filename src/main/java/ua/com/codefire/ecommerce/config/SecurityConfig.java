package ua.com.codefire.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;

/**
 * Created by human on 2/16/17.
 */
@EnableWebSecurity
public class SecurityConfig {

    private static final String QUERY_GET_USER = "SELECT user_name AS username, user_pass AS password " +
            "FROM users WHERE (username = ?, password = ?)";
    private static final String QUERY_USER_AUTHORITY = "SELECT users_roles AS username, user_access_lvl AS authority " +
            "FROM user_roles WHERE (username = ?)";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and().and()
                .jdbcAuthentication()
                .usersByUsernameQuery(QUERY_GET_USER)
//                .authoritiesByUsernameQuery(QUERY_USER_AUTHORITY)
        ;
    }
}
