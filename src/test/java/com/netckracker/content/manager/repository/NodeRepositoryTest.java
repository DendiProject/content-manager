/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.repository;


import com.netckracker.content.manager.JpaConfig;
import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.service.NodeServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.Test;
import org.mockito.Mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.hamcrest.CoreMatchers.equalTo;
import org.mockito.Mockito;

/**
 *
 * @author eliza
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
public class NodeRepositoryTest {

 
    @Autowired
    private NodeRepository nodeRepository;
    
  /*  @Before
    public void setUp() {
    Node node=new Node();
    node.setId("111");
 
    Mockito.when(nodeRepository.findById(node.getId()))
            .thenReturn(node);
      
}
    */
  /*  @Test
    public void whenFindByID_thenReturnNode() {
    // given
    Node node=new Node();
    node.setId("111"); 
    // when

    Node  found =new Node();
    found= nodeRepository.findById(node.getId());
 
    // then
    
    assertThat(found.getId(),equalTo(node.getId()));*/
//}
     @Test
    public void testFindAll() {
        List<Node> nodes = nodeRepository.findAll();
        assertThat(nodes.toString(), equalTo("[]"));
    }
    
    
}
