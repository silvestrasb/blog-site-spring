package lt.codeacademy.blog_site.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer{
    @Bean
    public PasswordEncoder encoder() {
/*
           THIS IS ONLY FOR DEVELOPMENT PURPOSES,
           MUST NOT BE USED IN FINAL VERSION.
           EVERY PASSWORD MUST BE PROPERLY ENCRYPTED.
 */
        return NoOpPasswordEncoder.getInstance();
    }
}
