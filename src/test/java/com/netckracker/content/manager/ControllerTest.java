/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.model.NodeType;
import com.netckracker.content.manager.model.Verb;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.repository.NodeTypeRepository;
import com.netckracker.content.manager.repository.VerbRepository;
import com.netckracker.content.manager.service.NodeService;
import com.netckracker.content.manager.service.StorageService;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
 
import java.util.Arrays;
 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    private VerbRepository verbRepository;
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
       // result.andExpect()
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
        String tagName="cooking";
        nodeService.addTag(nodeId1, tagName);
        nodeService.addTag(nodeId2, tagName);
        
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/node/bytag/"+tagName);
        request.param("page", "0");
        request.param("size", "4");
        request.contentType(MediaType.APPLICATION_JSON);
       MvcResult  result = mockMvc.perform(request).andReturn();        
       String expected = "[{id:"+nodeId1+"},{id:"+nodeId2+"}]";
       JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
    }
    @Test
    public void getNodesByVerb() throws Exception
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
        String verbName="verb";
        nodeService.addVerb(nodeId1, verbName);
        nodeService.addVerb(nodeId2, verbName);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/node/byverb/"+verbName);
        request.param("page", "0");
        request.param("size", "4");
        request.contentType(MediaType.APPLICATION_JSON);
        
       MvcResult  result = mockMvc.perform(request).andReturn();  
       System.out.println(result.getResponse()
				.getContentAsString());
       String expected = "[{id:"+nodeId1+"},{id:"+nodeId2+"}]";
       JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
        
    }
    @Test
    public void addNodeTest() throws Exception
    {
        File f=new File("src\\test\\resources\\WEB-INF\\images\\1.jpg");
        String fileName="1";
        String type="image/jpeg";
        String size=String.valueOf(f.length());
        String extension="jpg";
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
    
    @Test
    public void deleteNodeTest() throws Exception
    {
        Node node=new Node();
        node.setName("node");
        NodeType type=new NodeType();
        type.setName("image");
        NodeType savedType=nodeTypeRepository.save(type);
        node.setNodeType(savedType);
        Node saved=nodeRepository.save(node);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/node/deletenode/"+saved.getId());
        ResultActions result = mockMvc.perform(request);
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void addTextTest() throws Exception
    {
        String text="SomeText";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/node/addnodetext");
        request.param("textName", "Receipe Description");
        request.param("type", "text/plain");
        request.param("size", String.valueOf(text.length()));
        request.param("content", text);
        request.contentType(MediaType.APPLICATION_JSON);
        request.accept(MediaType.APPLICATION_JSON);
        ResultActions result = mockMvc.perform(request)
                 .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test 
    public void getTextTest() throws Exception
    {
        String text="SomeText";
        NodeDto node=nodeService.addNodeText("Receipe Description", null, "text/plain", String.valueOf(text.length()), text);
        MockHttpServletRequestBuilder request=MockMvcRequestBuilders.get("/node/getnodetext/"+node.getId());
         ResultActions result = mockMvc.perform(request)
                 .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test 
    public void deleteTextTest() throws Exception
    {
        String text="SomeText";
        NodeDto node=nodeService.addNodeText("Receipe Description", null, "text/plain", String.valueOf(text.length()), text);
        MockHttpServletRequestBuilder request=MockMvcRequestBuilders.delete("/node/deletenodetext/"+node.getId());
        ResultActions result = mockMvc.perform(request)
                 .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test 
    public void findVerbsByLettersTest() throws Exception
    {
        Verb verb1=new Verb();
        Verb verb2=new Verb();
        verb1.setName("someVerb1");
        verb2.setName("someVerb2");
        verbRepository.save(verb1);
        verbRepository.save(verb2);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/verb/"+"some");
        request.contentType(MediaType.APPLICATION_JSON);        
       MvcResult  result = mockMvc.perform(request).andReturn();  
       String expected = "[{name:"+verb1.getName()+"},{name:"+verb2.getName()+"}]";
       JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
        
        
    }
}
