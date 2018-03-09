package com.netckracker.content.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@ComponentScan({"com.netckracker.content.manager"})
@SpringBootApplication
public class App 
{
final static String IMAGE_RESOURCE_PATH = "/filestorage/";
    
    public static void main( String[] args ) throws FileNotFoundException, IOException 
    {        
	SpringApplication.run(App.class, args); 

    }
    @Bean
    public ModelMapper modelMapper() {
      return new ModelMapper();
    }
 
   
}
