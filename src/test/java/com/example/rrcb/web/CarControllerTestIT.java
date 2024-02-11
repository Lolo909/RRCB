package com.example.rrcb.web;

import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.Role;
import com.example.rrcb.model.entity.User;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.model.entity.enums.RoleNameEnum;
import com.example.rrcb.repository.CarRepository;
import com.example.rrcb.repository.CategoryRepository;
import com.example.rrcb.repository.UserRepository;
import com.example.rrcb.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTestIT {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    User testUser, testAdmin;
    Car testCarVintage, testCarAntique, testCarClassic;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @BeforeEach
    void setUp() {
        carRepository.deleteAll();
        carRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();

        List<Integer> allAvailableDaysListT = new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
            add(31);
        }};
        Category categoryVintage = new Category()
                .setName(CategoryNameEnum.VINTAGE)
                .setDescription("Manufactured between 1919 and 1930.");
        categoryRepository.save(categoryVintage);

        Category categoryAntique = new Category()
                .setName(CategoryNameEnum.ANTIQUE)
                .setDescription("Manufactured 1975 or earlier (>45 years old)");
        categoryRepository.save(categoryAntique);

        Category categoryClassic = new Category()
                .setName(CategoryNameEnum.CLASSIC)
                .setDescription("Manufactured 2000 or earlier (>20 years old)");
        categoryRepository.save(categoryClassic);

        testCarVintage = new Car()
                .setAllAvailableDays(allAvailableDaysListT)
                .setBrand("testBrand")
                .setName("testName")
                .setDescription("Very long description for test.")
                .setModel("testModel")
                .setCategory(categoryVintage)
                .setFile("ImageUrl with beautiful test image.")
                .setCreated(1919);
        testCarAntique = new Car()
                .setAllAvailableDays(allAvailableDaysListT)
                .setBrand("testBrand2")
                .setName("testName2")
                .setDescription("Very long description for test.2")
                .setModel("testModel2")
                .setCategory(categoryAntique)
                .setFile("ImageUrl with beautiful test image.2")
                .setCreated(1975);
        testCarClassic = new Car()
                .setAllAvailableDays(allAvailableDaysListT)
                .setBrand("testBrand3")
                .setName("testName3")
                .setDescription("Very long description for test.3")
                .setModel("testModel3")
                .setCategory(categoryClassic)
                .setFile("ImageUrl with beautiful test image.3")
                .setCreated(2000);

        carRepository.saveAndFlush(testCarVintage);
        carRepository.saveAndFlush(testCarAntique);
        carRepository.saveAndFlush(testCarClassic);


        userRepository.deleteAll();
        userRoleRepository.deleteAll();

        Role adminRole = new Role().setName(RoleNameEnum.ADMIN);
        Role userRole = new Role().setName(RoleNameEnum.USER);

        testUser = createTestUser();
        testUser.setRoles(List.of(userRole));

        testAdmin = createTestAdmin();
        testUser.setRoles(List.of(userRole, adminRole));


        userRoleRepository.save(userRole);
        userRoleRepository.save(adminRole);
        userRepository.save(testUser);
        userRepository.save(testAdmin);
    }

    @AfterEach
    void tearDown() {
        carRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "pesho123", roles = "USER")
    void testGetAllCarsShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("allCars"))
                .andExpect(model().attributeExists("cars"));
    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testGetAllCarsAdminPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/allCarsAdmin"))
                .andExpect(status().isOk())
                .andExpect(view().name("allCarsAdmin"))
                .andExpect(model().attributeExists("cars"));
    }

    @Test
    @WithMockUser(username = "pesho123", roles = "USER")
    void testGetAllVintageCarsShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/vintage"))
                .andExpect(status().isOk())
                .andExpect(view().name("allVintageCars"))
                .andExpect(model().attributeExists("cars"));
    }

    @Test
    @WithMockUser(username = "pesho123", roles = "USER")
    void testGetAllAntiqueCarsShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/antique"))
                .andExpect(status().isOk())
                .andExpect(view().name("allAntiqueCars"))
                .andExpect(model().attributeExists("cars"));
    }

    @Test
    @WithMockUser(username = "pesho123", roles = "USER")
    void testGetAllClassicCarsShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/classic"))
                .andExpect(status().isOk())
                .andExpect(view().name("allClassicCars"))
                .andExpect(model().attributeExists("cars"));
    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testGetAddCarPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-car"));
    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testGetAddCarWorksCorrectly() throws Exception {

        mockMvc.perform(post("/cars/add")
                        .param("name", "Test car name")
                        .param("description", "Test car description")
                        .param("brand", "Test car brand")
                        .param("model", "Test car model")
                        .param("created", "1919")
                        .param("imageUrl", "Test car imageUrl")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("all"));
    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testGetAddCarWorksWithInvalidData() throws Exception {

        mockMvc.perform(post("/cars/add")
                        .param("name", "")
                        .param("description", "Test car description")
                        .param("brand", "Test car brand")
                        .param("model", "Test car model")
                        .param("created", "1919")
                        .param("imageUrl", "Test car imageUrl")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("add"));
    }

    //TODO: TEST WITH OTHERS FIELDS INVALID DATA

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testRemoveCarPageShowsUp() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/cars/remove/{id}", testCarVintage.getId())
                        //.param("id", testCarVintage.getId().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cars/allCarsAdmin"));
    }


    @Test
    @WithMockUser(username = "pesho123", roles = "USER")
    void testDetailsCarPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/details/{id}", testCarVintage.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("car-details"))
                .andExpect(model().attributeExists("car"));
    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testGetEditCarPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/edit/{id}", testCarVintage.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("carForEdit"))
                .andExpect(view().name("car-edit"));
    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testGetEditCarThrowsException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/edit/96"))
                .andExpect(status().is4xxClientError());
                //.andExpect(view().name("object-not-found"))
                //.andExpect(model().attributeExists("objectId", "objectType"));
    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testEditCarWorksCorrectly() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/cars/edit/{id}", testCarVintage.getId())
                        .with(csrf())
                        .param("name", "new name")
                        .param("brand", "new brand")
                        .param("model", "new model")
                        .param("description", "new description")
                        .param("created", "1920")
                        .param("imageUrl", "new imageUrl"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cars/allCarsAdmin"));
    }

    @Test
    @WithMockUser(username = "admin123", roles = "ADMIN")
    void testEditCarWithInvalidData() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/cars/edit/{id}", testCarVintage.getId())
                        .with(csrf())
                        .param("name", "")
                        .param("brand", "new brand")
                        .param("model", "new model")
                        .param("description", "new description")
                        .param("created", "1920")
                        .param("imageUrl", "new imageUrl"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cars/edit/" + testCarVintage.getId().toString()));
    }

    @Test
    @WithMockUser(username = "pesho123", roles = "USER")
    void testGetRentCarPageShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/rent/{id}", testCarVintage.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("carForRent"))
                .andExpect(view().name("car-rent"));
    }

    @Test
    @WithMockUser(username = "pesho123", roles = "USER")
    void testGetRentCarThrowsException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/rent/96"))
                .andExpect(status().is4xxClientError());
        //.andExpect(view().name("object-not-found"))
        //.andExpect(model().attributeExists("objectId", "objectType"));
    }

    //TODO:
/*
    @Test
    @WithMockUser(username = "pesho123", roles = "USER")
    void testRentCarWorksCorrectly() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/cars/rent/{id}", testCarVintage.getId())
                        .with(csrf())
                        .param("allOrderDays", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cars/all"));
    }*/

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
