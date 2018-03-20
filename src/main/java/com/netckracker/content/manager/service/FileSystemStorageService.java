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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
    @Autowired
    private MetaInformationRepository metaRepository;  

    //@Value("${root.mount.path}")
  //  private  String path;
    final static String PATH = "/filestorage/";
    @Override
    public void store(byte[] content, String nodeId) {
        String checkSum=hash.getChecksum(content);
        List<Node> nodes=nodeRepository.findByCheckSum(checkSum);
        Node node=nodeRepository.findById(nodeId);
        
        String name=node.getName();
            String extension=new String();
           for (Iterator<MetaInformation> it = node.getMetaList().iterator(); it.hasNext(); ) {
                MetaInformation meta = it.next();
               if (meta.getMetaInformationType().equals("extension"))
               {
                    extension=meta.getValue();
               }            
            }
           File f=new File(PATH+name+"."+extension);
        if (nodes==null)
        {            
            try {
                FileOutputStream fos=new FileOutputStream(f);
                fos.write(content);
                fos.close();                             
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileSystemStorageService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileSystemStorageService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Path link = null;
        try {
            Files.createSymbolicLink(link, f.toPath());
        } catch (IOException ex) {
            Logger.getLogger(FileSystemStorageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        node.setNodeSource(link.toString());
    }



    @Override
    public void delete(String fileName) {
        File f=new File (PATH+fileName);
        f.delete();
    }

    @Override
    public byte[] load( String nodeId) throws FileNotFoundException, IOException {
        Node node=nodeRepository.findById(nodeId);
        File f=new File(node.getNodeSource());
        FileInputStream fin=new FileInputStream(f);
        byte[] content = new byte[fin.available()];

        fin.read(content, 0, fin.available());
        return content;
        
    }
    
}
