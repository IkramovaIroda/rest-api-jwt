package com.example.restapijwt.component;

import com.example.restapijwt.entity.User;
import com.example.restapijwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    //field metod
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    @Value("${spring.sql.init.mode}")
    String mode;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            userRepository.save(new User("user", passwordEncoder.encode("123")));
            userRepository.save(new User("admin", passwordEncoder.encode("123")));
            userRepository.save(new User("moderator", passwordEncoder.encode("123")));
        }
    }
}
