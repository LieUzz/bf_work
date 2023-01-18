package com.example.shoppingapp.dao;

import com.example.shoppingapp.dao.AbstractHibernateDAO;
import com.example.shoppingapp.entity.Permission;
import com.example.shoppingapp.entity.User;
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

    public void createNewPermission(Permission permission) {
        createData(permission);
    }
}

