/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager;



import com.netckracker.content.manager.model.NodeDto;
import com.netckracker.content.manager.service.FileSystemStorageService;
import com.netckracker.content.manager.service.NodeService;
import com.netckracker.content.manager.service.StorageService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author eliza
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FileControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private FileSystemStorageService storageService;
    @Autowired
    private WebApplicationContext wac;
    
    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(wac).build();
        storageService.setRootLocation("target/files/filestorage");
        storageService.init();
               
       
    }

    
    @Test
    public void addFileTest() throws Exception
    {
        URL resource = FileControllerTest.class.getResource("/WEB-INF/images/1.jpg");
        
        FileInputStream fis = new FileInputStream(Paths.get(resource.toURI()).toFile());
        MockMultipartFile f = new MockMultipartFile("file", "1.jpg",
                "image/jpeg",   fis);
        String fileName=new String("1");
        String type=new String("image/jpeg");
        String size=String.valueOf(f.getSize());
        String extension=new String("jpg");
        
        NodeDto nodeDto=nodeService.addNodeImg(f.getName(), type, null, size, extension);
        MockHttpServletRequestBuilder request=MockMvcRequestBuilders
                .fileUpload("/file/addfile/"+nodeDto.getId())
                .file(f);
        ResultActions result = mockMvc.perform(request)
                 .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void getFileTest() throws FileNotFoundException, IOException, Exception
    {
        URL resource = FileControllerTest.class.getResource("/WEB-INF/images/1.jpg");
        String type="image/jpeg";
        String size=String.valueOf(Paths.get(resource.toURI()).toFile().length());
        String extension="jpg";
        NodeDto nodeDto=nodeService.addNodeImg("image", type, null, size, extension);
        FileInputStream fin=new FileInputStream(Paths.get(resource.toURI()).toFile());
        byte[] content = new byte[fin.available()];
        fin.read(content, 0, fin.available());
        fin.close();
        storageService.store(content, nodeDto.getId());
        MockHttpServletRequestBuilder request=MockMvcRequestBuilders.get("/file/getfile/"+nodeDto.getId());
         ResultActions result = mockMvc.perform(request)
                 .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
