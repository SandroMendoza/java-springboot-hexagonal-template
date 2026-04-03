package com.projects.adapters.webapi.springboot;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
@Tag(name = "API", description = "All endpoints")
public interface ApiBase {}
