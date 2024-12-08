package com.example.rrcb.web;

import com.example.rrcb.model.entity.Car;
import com.example.rrcb.model.entity.Category;
import com.example.rrcb.model.entity.enums.CategoryNameEnum;
import com.example.rrcb.repository.CarRepository;
import com.example.rrcb.repository.CategoryRepository;
import com.example.rrcb.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class    HomeControllerTestIT {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CategoryRepository categoryRepository;



    @Test
    void testGetHomeShowsUp() throws Exception {
        List<Integer> allAvailableDaysListT = new ArrayList<>(){{ add(1); add(2); add(3); add(4); add(5); add(6); add(7); add(31);}};
        Category category = new Category()
                .setName(CategoryNameEnum.VINTAGE)
                .setDescription("Manufactured between 1919 and 1930.");
        categoryRepository.save(category);

        Car car1 = new Car()
        .setAllAvailableDays(allAvailableDaysListT)
                .setBrand("testBrand")
                .setName("testName")
                .setDescription("Very long description for test.")
                .setModel("testModel")
                .setCategory(category)
                .setFile("ImageUrl with beautiful test image.")
                .setCreated(1919);
        Car car2 = new Car()
                .setAllAvailableDays(allAvailableDaysListT)
                .setBrand("testBrand2")
                .setName("testName2")
                .setDescription("Very long description for test.2")
                .setModel("testModel2")
                .setCategory(category)
                .setFile("ImageUrl with beautiful test image.2")
                .setCreated(1919);
        carRepository.saveAndFlush(car1);
        carRepository.saveAndFlush(car2);

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testGetAboutShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }

    @Test
    void testGetRentsInfoShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rentsInfo"))
                .andExpect(status().isOk())
                .andExpect(view().name("rentsInfo"));
    }
}
