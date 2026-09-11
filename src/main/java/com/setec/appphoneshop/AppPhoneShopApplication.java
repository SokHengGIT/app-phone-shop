package com.setec.appphoneshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AppPhoneShopApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppPhoneShopApplication.class, args);
    }

    // Needed because packaging is WAR (allows deployment to an external
    // servlet container such as Tomcat, in addition to running as a jar).
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppPhoneShopApplication.class);
    }
}
