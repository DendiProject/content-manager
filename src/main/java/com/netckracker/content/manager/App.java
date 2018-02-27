package com.netckracker.content.manager;



import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.service.NodeService;
import com.netckracker.content.manager.service.NodeServiceImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.test.context.ContextConfiguration;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App 
{
    
    public static void main( String[] args ) 
    {        
	SpringApplication.run(App.class, args);     
   

                     
    }
   
}
