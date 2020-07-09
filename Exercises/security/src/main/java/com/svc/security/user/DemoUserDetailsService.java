package com.svc.security.user;

import com.svc.security.data.model.AuthorityEntity;
import com.svc.security.data.model.UserEntity;
import com.svc.security.data.repositories.UserEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DemoUserDetailsService implements UserDetailsService {
    private final UserEntityRepository userRepository;

    public DemoUserDetailsService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByName(username);
        return userEntity
                .map(this::map)
                .orElseThrow(
                        () -> new UsernameNotFoundException
                                ("User " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return new User(
                userEntity.getName(),
                userEntity.getPassword(),
                userEntity.getAuthorities()
                        .stream()
                        .map(this::map)
                        .collect(Collectors.toList())
        );
    }

    private GrantedAuthority map(AuthorityEntity authorityEntity) {
        return new SimpleGrantedAuthority(authorityEntity.getName());
    }
}
