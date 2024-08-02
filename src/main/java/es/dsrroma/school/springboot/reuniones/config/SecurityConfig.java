package es.dsrroma.school.springboot.reuniones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration//para definir bean
@EnableWebSecurity//define filtros de seguridad
public class SecurityConfig {
    //Abra dos roles, User y ApiUser
    //especificaremos como almacenar la contrasenia con un hash
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers("/api/*").hasRole("API_USER")
                .and().authorizeHttpRequests().requestMatchers("/*").authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails usuario1 = User.builder().username("Clarks")
                .password(passwordEncoder().encode("1234Gu"))
                .roles("USER", "API_USER").build();
        UserDetails usuario2 = User.builder().username("Elian")
                .password(passwordEncoder().encode("1234EL"))
                .roles("USER").build();
        //esto se debe hacer solo en aplicaciones de prueba
        return new InMemoryUserDetailsManager(usuario1, usuario2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
