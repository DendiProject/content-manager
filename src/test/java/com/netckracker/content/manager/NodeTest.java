/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager;

import static com.google.common.base.CharMatcher.is;
import com.netckracker.content.manager.contorller.Controller;
import com.netckracker.content.manager.contorller.FileController;
import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.model.TagDto;

import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.repository.TagRepository;
import com.netckracker.content.manager.service.NodeServiceImpl;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import sun.net.www.URLConnection;

/**
 *
 * @author eliza
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@ContextConfiguration(classes=App.class)
public class NodeTest {
    @Autowired
    private FileController controller;
    @Autowired
    private NodeServiceImpl nodeService;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static final String NAME="tag";
        
    @Test
    public void addTagTest(){
        Node node=new Node();
        node.setName("111");
        Node saved=nodeRepository.save(node);       
        nodeService.addTag(saved.getId(), NAME);
        Tag tag=tagRepository.findByName(NAME);
        assertEquals("name incorrect", NAME, tag.getName());  
        List<NodeDto>  nodes=nodeService.findByTag(NAME, 0, 1);
        assertEquals("id incorrect",  saved.getId(), nodes.get(0).getId());  
         
    }
    @Test
    public void findTags(){
        Tag tag1=new Tag();
        tag1.setName("tag1");
        Tag tag2=new Tag();
        tag2.setName("tag2");
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        List<TagDto> tags=nodeService.findTagByLetters("tag");
        assertEquals("tag incorrect", "tag1", tags.get(0).getName());
        assertEquals("tag incorrect", "tag2", tags.get(1).getName());

    }
    @Test 
    public void addNodeTest() throws InterruptedException, FileNotFoundException, IOException {
        
      /*  System.out.println("Start addNode test");
        
        FileInputStream fin=new FileInputStream(f);
        byte[] content=new byte[fin.available()];
        fin.read(content, 0, fin.available());
        int size=fin.available();
        fin.close();
        Map<String, String> newNode = new HashMap<>();
       newNode.put("content", Arrays.toString(content));
       newNode.put("type",  "image");
       newNode.put("userId", null);
       newNode.put("size", String.valueOf(size));
      */
    /*  Node n=new Node();
      n.setName("node");
      Node saved=nodeRepository.save(n);
      File f =new File ("E://1.jpg");
      String content = new String();
        Map<String, String> newFile = new HashMap<>();
        newFile.put("content", content);
        newFile.put("nodeId", saved.getId());
        rabbitTemplate.convertAndSend(newFile);
       */
     //  controller.addNode(content, "image",0 );
        
    }
 
}
