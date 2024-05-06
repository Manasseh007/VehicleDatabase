package SmartVehicleCRUD.smartVehicle.VehicleTracking.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
        {
            try {
                configurer
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vehicles").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/vehicles/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/vehicles").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/vehicles/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/vehicles/{id}").hasAnyRole("ADMIN", "USER")
                        .and()
                        .formLogin().loginPage("/login")
                        .defaultSuccessUrl("/logout", true).permitAll()
                        .and()
                        .logout().permitAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });



        // use HTTP Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request Forgery (CSRF)

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}

// Other Options to implement Security on this project
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//import java.beans.Customizer;

import static java.beans.Customizer.*;

@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails ramesh = User.builder()
                .username("ramesh")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails  admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ramesh, admin);
    }
}

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure in-memory authentication
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin123").roles("ADMIN")
                .and()
                .withUser("user").password("user123").roles("USER");
    }

    @Bean
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/admin/**").hasRole("ADMIN") // Secured URLs for admin role
                .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER") // Secured URLs for both roles
                .requestMatchers("/").permitAll() // Allow access to home page
                .and()
                .formLogin("/login"); // Enable form-based login
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // NoOpPasswordEncoder is used for demonstration purpose only. Replace with a secure encoder in production.
        return NoOpPasswordEncoder.getInstance();
    }
}
*/


/*
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/vehicles").hasAnyRole("USER", "ADMIN", "MANAGER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll());

        return http.build();
    }

* @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYER")
                .build();

        UserDetails mel = User.builder()
                .username("mel")
                .password("{noop}mel123")
                .roles("EMPLOYER")
                .build();

        UserDetails merry = User.builder()
                .username("merry")
                .password("{noop}mer123")
                .roles("EMPLOYER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mel, merry);
    }
    *
    * @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
* */