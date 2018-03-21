package com.netckracker.content.manager;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeType;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.repository.NodeTypeRepository;
import com.netckracker.content.manager.service.NodeService;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
