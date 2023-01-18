package com.example.login.dao;

import com.example.login.dao.AbstractHibernateDAO;
import com.example.login.entity.Permission;
import com.example.login.entity.User;
import com.example.login.exception.InvalidCredentialsException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("permissionDao")
public class PermissionDao extends AbstractHibernateDAO<Permission> {

    public PermissionDao() {
        setClazz(Permission.class);
    }

    public Permission getPermissionById(Integer id) {
        return findById(id);
    }
    public List<Permission> getAllPermission() {
        return loadAllData();
    }

    public void createNewPermission(Permission permission) {
        createData(permission);
    }

    public Optional<Permission> loadPermissionByDescription(String permissionDescription) {
        return loadAllData()
                .stream()
                .filter(a->a.getPermission_description().equals(permissionDescription))
                .findAny();
    }

}