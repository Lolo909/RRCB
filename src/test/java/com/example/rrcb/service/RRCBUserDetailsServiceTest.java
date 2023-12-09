package com.example.rrcb.service;

import com.example.rrcb.model.entity.Role;
import com.example.rrcb.model.entity.User;
import com.example.rrcb.model.entity.enums.RoleNameEnum;
import com.example.rrcb.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RRCBUserDetailsServiceTest {

    private RRCBUserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp(){
        serviceToTest = new RRCBUserDetailsService(mockUserRepository);
    }


    @AfterEach
    void tearDown(){
        mockUserRepository.deleteAll();
    }

    @Test
    void testUserNotFound(){

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("Pesho")
        );
    }

    @Test
    void testUserFoundException(){
        //Arrange
        User testUser = createTestUser();
        when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));
        //Act
        UserDetails userDetails =  serviceToTest.loadUserByUsername(testUser.getUsername());

        //Assert
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUser.getUsername(), userDetails.getUsername(), "Username is not populated properly.");

        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());

        Assertions.assertTrue(containsAuthorities(userDetails, "ROLE_"+ RoleNameEnum.ADMIN), "The user is not admin.");
        Assertions.assertTrue(containsAuthorities(userDetails, "ROLE_"+ RoleNameEnum.USER), "The user is not user.");

    }

    private boolean containsAuthorities(UserDetails userDetails, String expectedAuthority){
        return userDetails.getAuthorities().stream().anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static User createTestUser(){
        return new User()
                .setUsername("pesho123")
                .setFullName("Pesho Peshev")
                .setEmail("pesho@abv.bg")
                .setPassword("topSecret")
                .setRoles(List.of(new Role().setName(RoleNameEnum.USER),
                        new Role().setName(RoleNameEnum.ADMIN)));
    }
}
