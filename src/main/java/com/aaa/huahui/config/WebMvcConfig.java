package com.aaa.huahui.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    String file_base_path;
    String filepath_pattern;

    @Autowired
    public WebMvcConfig(@Value("${file_base_path}") String file_base_path, @Value("${filepath_pattern}") String filepath_pattern) {
        if (file_base_path.endsWith("/")) {
            this.file_base_path = file_base_path;
        } else {
            this.file_base_path = file_base_path + "/";
        }

        if (filepath_pattern.endsWith("/")) {
            this.filepath_pattern = filepath_pattern;
        } else {
            this.filepath_pattern = filepath_pattern + "/";
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(filepath_pattern + "/**").addResourceLocations("file://" + file_base_path);

    }
}
