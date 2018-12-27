package org.app.front.office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppFrontOfficeApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(AppFrontOfficeApplication.class, args);
    }
}
