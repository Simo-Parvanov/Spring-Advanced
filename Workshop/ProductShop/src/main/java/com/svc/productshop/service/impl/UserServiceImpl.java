package com.svc.productshop.service.impl;


import com.svc.productshop.domain.entities.User;
import com.svc.productshop.domain.models.service.UserServiceModel;
import com.svc.productshop.repository.UserRepository;
import com.svc.productshop.service.RoleService;
import com.svc.productshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, RoleService roleService, ModelMapper mapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        roleService.seedRolesInDB();
        if (userRepository.count() == 0){
            userServiceModel.setAuthorities(roleService.findAllRoles());
        }else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            userServiceModel.getAuthorities().add(roleService.findRoleByAuthority("ROLE_USER"));
        }
        User user = mapper.map(userServiceModel, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        userRepository.saveAndFlush(user);

        return mapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));
    }
}
