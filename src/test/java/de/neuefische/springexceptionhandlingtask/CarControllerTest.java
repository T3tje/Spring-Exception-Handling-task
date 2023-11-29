package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired private MockMvc mvc;

    @DirtiesContext
    @Test
    void getCarBrand_whenRequestWithPorsche_shouldReturnStringOf_porsche() throws Exception {
        //GIVEN
        mvc.perform(MockMvcRequestBuilders.get("/api/cars/porsche"))
        //WHEN
        //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("porsche"));
    }

    @DirtiesContext
    @Test
    void getCarBrand_whenRequestWithNonPorscheBrand_shouldReturnBadRequest() throws Exception {
        // WHEN
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/cars/vw"))
                // THEN
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        Exception resolvedException = result.getResolvedException();
        assertNotNull(resolvedException);
        assertTrue(resolvedException instanceof IllegalArgumentException);
        assertEquals("Only 'porsche' allowed", resolvedException.getMessage());
    }
}