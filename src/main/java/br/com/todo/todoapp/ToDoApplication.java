package br.com.todo.todoapp;

import org.apache.myfaces.webapp.StartupServletContextListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContextListener;

@SpringBootApplication
public class ToDoApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean facesServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(), "*.jsf");
        registration.setName("Faces Servlet");
        return registration;
    }

    @Bean
    public ServletContextListener startupServerContextListener() {
        return new StartupServletContextListener();
    }

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/index.jsf" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
    }

}
