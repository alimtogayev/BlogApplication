package alim.project.blogapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
                .password("pass1")
                .roles()
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password("pass2")
                .roles()
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
