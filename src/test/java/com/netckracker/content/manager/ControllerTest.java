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
import com.netckracker.content.manager.service.NodeService;
import java.io.File;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.is;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author eliza
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private NodeTypeRepository nodeTypeRepository;
    @Autowired
    private WebApplicationContext wac;
    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(wac).build();

    }
    @Test
    public void addingTagTest() throws Exception
    {
       Node node=new Node();
        node.setName("node");
        NodeType type=new NodeType();
        type.setName("image");
        NodeType savedType=nodeTypeRepository.save(type);
        node.setNodeType(savedType);
        Node saved=nodeRepository.save(node);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/tag/addtag/"+ saved.getId());
        request.param("tagName", "someta");
        request.contentType(MediaType.APPLICATION_JSON);
       ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void addingVerbTest() throws Exception
    {
         Node node=new Node();
        node.setName("node");
        NodeType type=new NodeType();
        type.setName("image");
        NodeType savedType=nodeTypeRepository.save(type);
        node.setNodeType(savedType);
        Node saved=nodeRepository.save(node);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/verb/addverb/"+ saved.getId());
        request.param("verbName", "nodeverb");
        request.contentType(MediaType.APPLICATION_JSON);
       ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void getNodesByTagTest () throws Exception
    {
        
        Node n1=new Node();
        Node n2=new Node();
        n1.setName("node1");
        n2.setName("node2");
        NodeType type=new NodeType();
        type.setName("image");
        NodeType savedType=nodeTypeRepository.save(type);
        n1.setNodeType(savedType);
        n2.setNodeType(savedType);
        Node saved1=nodeRepository.save(n1);
        Node saved2=nodeRepository.save(n2);
        String nodeId1=saved1.getId();
        String nodeId2=saved2.getId();
        String tagName="nodeta";
        nodeService.addTag(nodeId1, tagName);
        nodeService.addTag(nodeId2, tagName);
      /*  MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("tag/"+ tagName);
        request.contentType(MediaType.APPLICATION_JSON);
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(jsonPath("$.id", is(saved1.getId())));*/
    }
    @Test
    public void addNodeTest() throws Exception
    {
        File f=new File("src\\test\\resources\\WEB-INF\\images\\1.jpg");
        String fileName=new String("1");
        String type=new String("image/jpeg");
        String size=new String(String.valueOf(f.length()));
        String extension=new String("jpg");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/node/addnodeimg");
        request.param("fileName", fileName);
        request.param("type", type);
        request.param("size", size);
        request.param("extension", extension);
        request.contentType(MediaType.APPLICATION_JSON);
        request.accept(MediaType.APPLICATION_JSON);
       ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }      
    
}
