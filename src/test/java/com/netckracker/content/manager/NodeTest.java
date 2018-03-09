/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.model.NodeType;
import com.netckracker.content.manager.model.Tag;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.repository.TagRepository;
import com.netckracker.content.manager.service.NodeService;
import com.netckracker.content.manager.service.NodeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author eliza
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class NodeTest {
    @Autowired
    private NodeServiceImpl nodeService;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private TagRepository tagRepository;
    private static final String NAME="tag";
        
    @Test
    public void addTagTest(){
        Node node=new Node();
        node.setName("111");
        Node saved=nodeRepository.save(node);
        System.out.println(saved.getId());
        nodeService.addTag(saved.getId(), NAME);
        Tag tag=tagRepository.findByName(NAME);
        assertEquals("name incorrect", NAME, tag.getName());  
         List<NodeDto>  nodes=nodeService.findByTag(tag.getId(), 0, 1);
    }
    
    
    @Test
    public void findNodeByTagTest(){
        new ArrayList<NodeDto>();   
         Tag tag=new Tag();
         tag=tagRepository.findByName(NAME);        
       
        //System.out.println(nodes.get(0).getId());
        //String nodeId=nodes.
        //assertEquals("name incorrect", "111", )
    }   
    
    
}
