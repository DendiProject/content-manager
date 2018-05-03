package com.netckracker.content.manager;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Hello world!
 *
 */

@ComponentScan({"com.netckracker.content.manager"})
@SpringBootApplication
public class App 
{

    public static void main( String[] args ) 
    {     
     
    SpringApplication.run(App.class, args);    

    }
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper=new ModelMapper();
      return  new ModelMapper();
    }  

}
