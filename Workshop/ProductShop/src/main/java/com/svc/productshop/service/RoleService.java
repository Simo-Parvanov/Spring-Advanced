package com.svc.productshop.service;

import com.svc.productshop.domain.models.service.RoleServiceModel;
import com.svc.productshop.domain.models.service.UserServiceModel;

import java.util.Set;

public interface RoleService {
    void seedRolesInDB();
    Set<RoleServiceModel> findAllRoles();
    RoleServiceModel findRoleByAuthority(String authority);
}
