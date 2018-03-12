package com.netckracker.content.manager;

import com.netckracker.content.manager.resource.Resource;
import java.util.concurrent.LinkedBlockingQueue;
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

    public static void main( String[] args )  
    {        
	SpringApplication.run(App.class, args);      
    }

    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper=new ModelMapper();
      return  new ModelMapper();
    }
    @Bean
    public LinkedBlockingQueue<Resource> blockingQueue() {
        return  new LinkedBlockingQueue<>();
    }
            
 
   
}
