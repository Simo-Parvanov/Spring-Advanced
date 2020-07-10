package com.svc.productshop.service.impl;

import com.svc.productshop.domain.entities.Role;
import com.svc.productshop.domain.models.service.RoleServiceModel;
import com.svc.productshop.repository.RoleRepository;
import com.svc.productshop.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    public void seedRolesInDB() {
        if (roleRepository.count() == 0){
            roleRepository.saveAndFlush(new Role("ROLE_USER"));
            roleRepository.saveAndFlush(new Role("ROLE_MODERATOR"));
            roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));
            roleRepository.saveAndFlush(new Role("ROLE_ROOT"));
        }
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return roleRepository
                .findAll()
                .stream()
                .map(role -> mapper.map(role, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findRoleByAuthority(String authority) {
        return mapper.map(authority, RoleServiceModel.class);

    }
}
