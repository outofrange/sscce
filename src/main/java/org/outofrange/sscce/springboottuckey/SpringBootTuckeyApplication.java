package org.outofrange.sscce.springboottuckey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@SpringBootApplication
public class SpringBootTuckeyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTuckeyApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean tuckeyRegistrationBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new UrlRewriteFilter());
        registrationBean.addInitParameter("confPath", "urlrewrite.xml");

        return registrationBean;
    }
}
