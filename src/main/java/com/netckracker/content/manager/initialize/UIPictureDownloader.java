/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager.initialize;

import com.netckracker.content.manager.model.Node;
import com.netckracker.content.manager.model.NodeType;
import com.netckracker.content.manager.repository.NodeRepository;
import com.netckracker.content.manager.repository.NodeTypeRepository;
import com.netckracker.content.manager.service.FileSystemStorageService;
import com.netckracker.content.manager.service.NodeService;
import com.netckracker.content.manager.service.StorageService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author eliza
 */
@Component
public class UIPictureDownloader implements  InitializingBean{
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private FileSystemStorageService storageService;
    

    @Override
    public void afterPropertiesSet() throws Exception {
        File file = new File(".").getAbsoluteFile();
        File root = file.getParentFile();
        while (root.getParentFile() != null) {
            root = root.getParentFile();
        }
        File f=new File(root.getPath()+"/UIPictures.zip");
        if (f.exists())
        {
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(f))) {
                ZipEntry ze ;
                String nextFileName;
                byte[] array;
                String fileId;
                while((ze = zis.getNextEntry()) != null)  {
                    nextFileName = ze.getName();
                    fileId=FilenameUtils.removeExtension(nextFileName);
                    Node newNode=nodeRepository.findById(fileId);
                    if (newNode!=null&&newNode.getNodeSource()==null)
                    {
                        newNode.setName(nextFileName);
                        
                        File newFile = new File(storageService.getRootLocation()+"/" + nextFileName);
                        FileOutputStream fos = new FileOutputStream(newFile);
                        int len;
                        array=new byte[(int)ze.getSize()];
                        while ((len = zis.read(array)) > 0) {
                            fos.write(array, 0, len);
                        }
                        fos.close();
                        newNode.setNodeSource(newFile.getAbsolutePath());
                        nodeRepository.save(newNode);
                    }
                    zis.closeEntry();
                }
                zis.close();
            }
        }
    }
}
