package org.sb;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.lightadmin.api.config.LightAdmin;
import org.lightadmin.core.config.LightAdminConfiguration;
import org.lightadmin.core.config.context.LightAdminViewConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LightAdminBootApplication extends SpringBootServletInitializer {
 
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        LightAdmin.configure(servletContext)
                .basePackage("org.sb.lightadmin")
                .baseUrl("/admin")
                .security(false)
                .backToSiteUrl("http://lightadmin.org");
 
        super.onStartup(servletContext);
    }
 
    public static void main(String[] args) throws Exception {
        SpringApplication.run(LightAdminBootApplication.class, args);
    }
 
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LightAdminBootApplication.class);
    }
}
