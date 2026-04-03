package com.projects.adapters.webapi.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration(classes = {SpringWebMvcConfiguration.class})
public abstract class WebMvcTestBase {

    @Autowired
    protected MockMvc mockMvc;
}
