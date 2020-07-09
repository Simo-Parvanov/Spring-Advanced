package com.svc.security;

import com.svc.security.data.model.AuthorityEntity;
import com.svc.security.data.model.UserEntity;
import com.svc.security.data.repositories.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityImplementInit implements CommandLineRunner {
    private final UserEntityRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityImplementInit(UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity();
        user.setName("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setEnabled(true);

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setName("ROLE_USER");
        authorityEntity.setUser(user);

        user.setAuthorities(List.of(authorityEntity));
        userRepository.save(user);

        UserEntity admin = new UserEntity();
        admin.setName("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEnabled(true);

        AuthorityEntity adminUser = new AuthorityEntity();
        adminUser.setName("ROLE_USER");
        adminUser.setUser(admin);

        AuthorityEntity adminAdmin = new AuthorityEntity();
        adminAdmin.setName("ROLE_ADMIN");
        adminAdmin.setUser(admin);

        admin.setAuthorities(List.of(adminUser, adminAdmin));
        userRepository.save(admin);
    }
}
