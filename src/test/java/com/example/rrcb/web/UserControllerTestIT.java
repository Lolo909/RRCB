package com.example.rrcb.web;

import com.example.rrcb.model.entity.Role;
import com.example.rrcb.model.entity.User;
import com.example.rrcb.model.entity.enums.RoleNameEnum;
import com.example.rrcb.repository.CategoryRepository;
import com.example.rrcb.repository.UserRepository;
import com.example.rrcb.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTestIT {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    User testUser, testAdmin;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

//    @Autowired
//    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();

        Role adminRole = new Role().setName(RoleNameEnum.ADMIN);
        Role userRole = new Role().setName(RoleNameEnum.USER);

        testUser = createTestUser();
        testUser.setRoles(List.of(userRole));

        testAdmin = createTestAdmin();
        testUser.setRoles(List.of(userRole, adminRole));

        userRepository.save(testUser);
        userRepository.save(testAdmin);

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();

    }

    @Test
    @WithMockUser(username = "pesho123", roles = "USER")
    void testGetUserProfileShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profileUser"))
                .andExpect(model().attributeExists("user"));
    }

    //    @Test
//    @WithMockUser(username = "admin123", roles = "USER")
//    void testUserGetProfileWithIdShowsUp() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile/{id}",testUser.getId()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("user-profile"))
//                .andExpect(model().attributeExists("user"));
//    }
    @Test
    @WithMockUser(username = "pesho123", roles = "USER")
    void testGetUserRentsShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/rents"))
                .andExpect(status().isOk())
                .andExpect(view().name("rents"))
                .andExpect(model().attributeExists("rents"));
    }


    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testGetAllUsersPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("allUsersAdmin"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testGetAllRentsPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/allRents"))
                .andExpect(status().isOk())
                .andExpect(view().name("allRentsAdmin"))
                .andExpect(model().attributeExists("allRents"));
    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testRemoveUserPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/remove/{id}", testUser.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/all"));

    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testChangeRoleUserPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/changeRole/{id}", testUser.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/all"));

    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testProfileEditUserPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile/edit/{id}", testUser.getId()))
                .andExpect(view().name("user-edit"))
                .andExpect(model().attributeExists("profileForEdit"));

    }

    private static User createTestAdmin() {
        return new User()
                .setUsername("admin123")
                .setFullName("Admin Adminov")
                .setEmail("admin@abv.bg")
                .setPassword("topSecretA");
    }

    private static User createTestUser() {
        return new User()
                .setUsername("pesho123")
                .setFullName("Pesho Peshev")
                .setEmail("pesho@abv.bg")
                .setPassword("topSecretU");
    }
}
