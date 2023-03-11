package org.viktoriianikitiuk.artoffreedom.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.viktoriianikitiuk.artoffreedom.config.PasswordEncoderConfig;
import org.viktoriianikitiuk.artoffreedom.config.WebSecurityConfig;
import org.viktoriianikitiuk.artoffreedom.dao.PaintingRepository;
import org.viktoriianikitiuk.artoffreedom.dao.UserRepository;
import org.viktoriianikitiuk.artoffreedom.model.Painting;
import org.viktoriianikitiuk.artoffreedom.service.RoleService;
import org.viktoriianikitiuk.artoffreedom.service.UserService;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import({WebSecurityConfig.class, PasswordEncoderConfig.class})
@WebMvcTest(AdminPaintingController.class)
class AdminPaintingControllerTest {

    @MockBean
    PaintingRepository paintingRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RoleService roleService;

    @MockBean
    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldDenyAccessForAnonymousUser() throws Exception {
        this.mockMvc
                .perform(get("/admin/painting"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void shouldAllowAccessForAdminUser() throws Exception {
        given(paintingRepository.findAll())
                .willReturn(Arrays.asList(new Painting("name", "author", "image.jpg", 11)));

        this.mockMvc
                .perform(get("/admin/painting"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminPainting"))
                .andExpect(model().attributeExists("existingPaintings"))
                .andExpect(model().attributeExists("addPaintingForm"));
    }
}