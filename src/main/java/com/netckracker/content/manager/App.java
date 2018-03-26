package com.netckracker.content.manager;

import com.netckracker.content.manager.service.StorageService;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
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

    public static void main( String[] args ) throws FileNotFoundException, IOException, InterruptedException  
    {        
	SpringApplication.run(App.class, args);    
       

    }
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper=new ModelMapper();
      return  new ModelMapper();
    }  
    
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {            
            storageService.init();
        };
    }
}
