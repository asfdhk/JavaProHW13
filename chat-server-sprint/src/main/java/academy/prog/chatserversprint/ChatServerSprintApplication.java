package academy.prog.chatserversprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@SpringBootApplication
@EnableWebSecurity
public class ChatServerSprintApplication {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(option -> option.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/test").hasRole("ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/add").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/get").permitAll();
                    auth.requestMatchers("/file").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/private").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/allUsers").permitAll();
                })
                .httpBasic(withDefaults());

        return http.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ChatServerSprintApplication.class, args);
    }
}
