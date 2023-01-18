package com.example.shoppingapp.service;

import com.example.shoppingapp.dao.PermissionDao;
import com.example.shoppingapp.dao.UserDao;
import com.example.shoppingapp.entity.Permission;
import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.service.entityService.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Disabled
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    //    @Spy
    @Mock
    UserDao userDAO;

    @Mock
    PermissionDao permissionDao;

    User mockUser;

    Permission mockPermission;

    @BeforeEach
    public void setup() {
        mockPermission = Permission.builder().id(1).permission_description("user").build();
        mockUser = User.builder()
                .id(1)
                .email("userTest@gmail.com")
                .username("userTest")
                .password("userTestPassword")
                .permission(mockPermission)
                .build();
    }


    @Test
    @DisplayName("get employee by id success scenario")
    public void testGetUserById_success() {
        when(userDAO.getUserById(1)).thenReturn(mockUser); //means we do not actual call employeeDAO.getEmployeeById(1)
//        System.out.println(employeeDAO.spying());
//        doReturn(mockEmployee).when(employeeDAO).getEmployeeById(1); // use it while using @Spy
        User user = userService.getUserById(1);
        assertEquals(mockUser, user);
    }
}