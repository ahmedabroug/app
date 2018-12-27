package org.app.config.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class AppConfigModuleApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(AppConfigModuleApplication.class, args);
    }
}
