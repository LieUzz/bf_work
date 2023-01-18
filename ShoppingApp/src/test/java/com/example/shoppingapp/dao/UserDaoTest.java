package com.example.shoppingapp.dao;


import com.example.shoppingapp.entity.Permission;
import com.example.shoppingapp.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@ActiveProfiles(value = "test")
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Autowired
    PermissionDao permissionDao;

    User mockUser;

    Permission mockPermission;

    @BeforeEach
    public void setup() {
        mockPermission = Permission.builder().permission_description("user").build();
        mockUser = User.builder()
                .username("userTest")
                .permission(mockPermission)
                .password("$2a$12$EhCS0Vbgs51u41UNvQcwLe5lnoID9a4ojBwIzHYeHowmLkdYhUzhu")
                .email("userTest@email.com")
                .build();
    }

    @Test
    @Transactional
    public void testGetPermissionById_found() {
        permissionDao.createNewPermission(mockPermission);
        Integer id = mockPermission.getId();
        assertNotNull(id);
        assertEquals(mockPermission, permissionDao.getPermissionById(id));
        System.out.println(mockPermission.toString());
        mockPermission.setId(null);

    }



    @Test
    @Transactional
    public void testGetUserById_found() {
        permissionDao.createNewPermission(mockPermission);
        mockUser.setPermission(mockPermission);
        userDao.createNewUser(mockUser);
        Integer id = mockUser.getId();
        assertNotNull(id);
        assertEquals(mockUser, userDao.getUserById(id));
        mockUser.setId(null);
        mockPermission.setId(null);
    }
}
