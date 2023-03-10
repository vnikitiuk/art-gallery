package org.viktoriianikitiuk.artoffreedom.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.viktoriianikitiuk.artoffreedom.config.WebSecurityConfig;
import org.viktoriianikitiuk.artoffreedom.dao.PaintingRepository;
import org.viktoriianikitiuk.artoffreedom.model.Painting;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(WebSecurityConfig.class)
@WebMvcTest(GalleryController.class)
class GalleryControllerTest {

    @MockBean
    PaintingRepository paintingRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAllowAccessForAnonymousUser() throws Exception {
        given(paintingRepository.findAll())
                .willReturn(Arrays.asList(new Painting("name", "author", "image.jpg", 11)));

        this.mockMvc
                .perform(get("/gallery"))
                .andExpect(status().isOk())
                .andExpect(view().name("gallery"))
                .andExpect(model().attributeExists("paintings"));
    }
}