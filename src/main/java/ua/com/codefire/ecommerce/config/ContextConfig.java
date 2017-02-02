package ua.com.codefire.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by human on 1/31/17.
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class ContextConfig {

    @Autowired
    private Environment env;
}
