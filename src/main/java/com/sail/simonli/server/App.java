/**
 * 
 */
package com.sail.simonli.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author admin
 *
 */
@SpringBootApplication
@ComponentScan
@EnableSwagger2
@EnableAsync
@MapperScan("com.sail.simonli.server.dao")
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication application=new SpringApplication(App.class);
		application.setBannerMode(Mode.OFF);
		application.run(args);
    }
}
