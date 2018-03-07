/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeType;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.repository.NodeTypeRepository;
import com.netckracker.content.manager.service.NodeServiceImpl;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author eliza
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@TestPropertySource(locations = {"classpath:application-test.yml"})
@Transactional
public class InmemoryDbTest {
    @Autowired
    private NodeTypeRepository nodeTypeRepository;
    
        @Autowired
    private NodeServiceImpl nodeServiceImpl;
    
    private static final String NAME="image";
    
    @Test
    public void givenNode_whenSave_thenGetOk(){
        NodeType type=new NodeType();        
        type.setName(NAME);
        nodeTypeRepository.save(type);
        NodeType saved =nodeTypeRepository.findByTypeId(type.getTypeId());
        
        assertEquals("name correct", NAME, saved.getName());        
    }   
    
   /* @Test
    public void test() throws IOException{
    
    byte [] array;
        String path="E:/travis.txt";
        path=URLEncoder.encode(path, "UTF-8"); 
        array = Files.readAllBytes(Paths.get(path));
        //System.out.println(getFilesDir());
        System.out.println(array);
        String typeName="";
        String s=nodeServiceImpl.addNode(array, typeName, true);
}
  */
}
