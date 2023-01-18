package com.example.login.service;


import com.example.login.dao.PermissionDao;
import com.example.login.dao.UserDao;
import com.example.login.entity.Permission;
import com.example.login.entity.User;
import com.example.login.exception.InvalidCredentialsException;
import com.example.login.security.AuthUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final PermissionDao permissionDao;


    @Autowired
    public UserService(UserDao userDao, PermissionDao permissionDao) {
        this.userDao = userDao;
        this.permissionDao = permissionDao;
    }

    @Transactional
    public List<User> getAllUserHibernate() {
        List<User> users = userDao.getAllUsers();

        for (User u : users)
            System.out.println(u.toString());
        return users;
    }

    public void createNewUser(User user) {
        userDao.createNewUser(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userDao.loadUserByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userDao.loadUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userDao.loadUserByUsername(username);

        if (!userOptional.isPresent()){
            throw new UsernameNotFoundException("Username does not exist");
        }

        User user = userOptional.get(); // database user
        return AuthUserDetail.builder() // spring security's userDetail
                .username(user.getUsername())
//                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .password(user.getPassword())
                .authorities(getAuthoritiesFromUser(user))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    public Permission getPermission(String permissionDescription) {
        Optional<Permission> permissionOp = permissionDao.loadPermissionByDescription(permissionDescription);
        Permission permission;
        if (permissionOp.isPresent()) {
            permission = permissionOp.get();
        } else {
            permission = Permission.builder().permission_description(permissionDescription).build();
            permissionDao.createNewPermission(permission);
        }
        return permission;
    }

    private List<GrantedAuthority> getAuthoritiesFromUser(User user){
        List<GrantedAuthority> userAuthorities = new ArrayList<>();

//        for (Permission permission :  user.getPermissions()){
//            userAuthorities.add(new SimpleGrantedAuthority(permission.getPermission_description()));
//        }
        userAuthorities.add(new SimpleGrantedAuthority(user.getPermission().getPermission_description()));

        return userAuthorities;
    }

}
