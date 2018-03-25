/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.service;

import com.netckracker.content.manager.Hash;
import com.netckracker.content.manager.model.MetaInformation;
import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.repository.MetaInformationRepository;
import com.netckracker.content.manager.repository.NodeRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author eliza
 */
@Service
public class FileSystemStorageService implements StorageService{
    @Autowired
    private Hash hash;
    @Autowired
    private NodeRepository nodeRepository;    

    final static String PATH = "filestorage";
    @Override
    public void store(byte[] array, String nodeId) { 
       
        Node node=nodeRepository.findById(nodeId);
        if (node!=null)
        {
            String checkSum=hash.getChecksum(array);
            List<Node> nodes=nodeRepository.findByCheckSum(checkSum);
            node.setCheckSum(checkSum);
            if (nodes.isEmpty())
            {            
                File f=new File("/"+PATH+"/"+node.getName());
                try {
                    FileOutputStream fos=new FileOutputStream(f);
                    fos.write(array);
                    fos.close();                             
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FileSystemStorageService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FileSystemStorageService.class.getName()).log(Level.SEVERE, null, ex);
                }
                node.setNodeSource(f.getAbsolutePath());
            }
            else
            {
                node.setNodeSource(nodes.get(0).getNodeSource());                
            }
            nodeRepository.save(node);
        }  
    }
    
    @Override
    public void delete(String fileName) {
        File f=new File (fileName);
        f.delete();
    }

    @Override
    public byte[] load( String nodeId) throws FileNotFoundException, IOException {
         Node node=nodeRepository.findById(nodeId);
         byte[] content=null;
        if (node!=null)
        {
            FileInputStream fin=new FileInputStream(node.getNodeSource());
            content= new byte[fin.available()];
            fin.read(content, 0, fin.available());
            fin.close();
        }
        return content;          
    }

    @Override
    public void init() {    
          File f=new File("/"+PATH);
        if (!f.exists()) {
            f.mkdir();
        }
    }
}
