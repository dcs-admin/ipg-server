/**
 * 
 */
package com.rama.ipg.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ram
 *
 */
@Component
@PropertySource("classpath:application.properties")
public class StorageService {
	
	private Logger logger=  LoggerFactory.getLogger(StorageService.class);
	
	@Value("${hostelsRootLocation:uploads/hostels}")
	private String hostelRootLocation;
	
	@Value("${tenantRootLocation:uploads/users}")
	private String tenantRootLocation;
	
	
	
	/**
	 * Stores hostel images with catagory wise
	 * 
	 * @param file
	 * @param cat
	 * @param id
	 * @throws Exception
	 */
	public  void storeHostelImage(MultipartFile file, String cat, Long id, String picnum) throws Exception {
		 
		logger.info("In::storeHostelImages::"+file+"::"+id+"::"+cat+"::"+picnum); 
		 
		InputStream inputStream = null;
		try {
			
		new File(hostelRootLocation).mkdir();
			 
		inputStream = file.getInputStream();
		
		String targetDir = hostelRootLocation+File.separator+id; 
		new File(targetDir).mkdir(); 
		targetDir = targetDir+File.separator+cat;
		new File(targetDir).mkdir();
		
	    File targetFile = new File(targetDir+File.separator+picnum);
	 
	    java.nio.file.Files.copy( inputStream,  targetFile.toPath(),  StandardCopyOption.REPLACE_EXISTING);
	   
		} catch (IOException e) {
			 throw new Exception("Unable to create file: "+e.getMessage());
		}finally{
			if( inputStream != null){
			 IOUtils.closeQuietly(inputStream);
			}
		}
		logger.info("OUT::POST:://hostels/uploadImage/{cat}/{id}::uploadHostelImages::"+id+"::"+cat); 

	}
	
	
	public List<Resource> retriveHostelImages(){
		
		return null;
		
	}
	
	public  InputStream retriveHostelImage(Long id, String cat) throws IOException {
		 
		File dir = new File(hostelRootLocation+File.separator+id+File.separator+cat);
		if(dir.isDirectory()){
			File file = dir.listFiles()[0]; 
		    InputStream targetStream = new FileInputStream(file); 
	       return targetStream;
		}
		
		return null;
    }
	
	
	public List<InputStreamResource> retriveHostelImages(Long id, String cat) throws IOException {
		 
		File dir = new File(hostelRootLocation+File.separator+id+File.separator+cat);
		 
		List<InputStreamResource> iss = new ArrayList<InputStreamResource>();
		if(dir.isDirectory()){
			for(File file : dir.listFiles()){
				InputStream targetStream = new FileInputStream(file);  
				iss.add(new InputStreamResource(targetStream));
				//targetStream.close(); 
			} 
		}
		
		 return iss;
    }


	
	
	public  void storeTenantImage(MultipartFile file, String cat, Long id) throws Exception {
		 
		logger.info("In::storeTenantImages::"+file+"::"+id+"::"+cat); 
		 
		InputStream inputStream = null;
		try {
						
		new File(tenantRootLocation).mkdir();
			 
		inputStream = file.getInputStream();
		
		String targetDir = tenantRootLocation+File.separator+id; 
		new File(targetDir).mkdir(); 
		targetDir = targetDir+File.separator+cat;
		new File(targetDir).mkdir();
		
	    File targetFile = new File(targetDir+File.separator+"MyPic");
	 
	    java.nio.file.Files.copy( inputStream,  targetFile.toPath(),  StandardCopyOption.REPLACE_EXISTING);
	   
		} catch (IOException e) {
			 throw new Exception("Unable to create file: "+e.getMessage());
		}finally{
			if( inputStream != null){
			 IOUtils.closeQuietly(inputStream);
			}
		}
		logger.info("OUT::POST:://tenant/uploadImage/{cat}/{id}::uploadtenantImages::"+id+"::"+cat); 

	}
	
	
	public List<Resource> retrivetenantImages(){
		
		return null;
		
	}
	
	public  InputStreamResource retrivetenantImage(Long id, String cat) throws IOException {
		 
		File dir = new File(tenantRootLocation+File.separator+id+File.separator+cat);
		InputStreamResource inputStreamResource= null;
		if(dir.isDirectory()){
			File file = dir.listFiles()[0]; 
			InputStream targetStream = new FileInputStream(file); 
			inputStreamResource = new InputStreamResource(targetStream);
			//targetStream.close();
	       //return targetStream;
		} 
		
		return inputStreamResource;
    }

	


public  void storeIdproofImage(MultipartFile file, String cat, Long id) throws Exception {
	 
	logger.info("In::storeIdproofImages::"+file+"::"+id+"::"+cat); 
	 
	InputStream inputStream = null;
	try {
		
	new File(tenantRootLocation).mkdir();
		 
	inputStream = file.getInputStream();
	
	String targetDir = tenantRootLocation+File.separator+id; 
	new File(targetDir).mkdir(); 
	targetDir = targetDir+File.separator+cat;
	new File(targetDir).mkdir();
	
    File targetFile = new File(targetDir+File.separator+file.getOriginalFilename());
 
    java.nio.file.Files.copy( inputStream,  targetFile.toPath(),  StandardCopyOption.REPLACE_EXISTING);
   
	} catch (IOException e) {
		 throw new Exception("Unable to create file: "+e.getMessage());
	}finally{
		if( inputStream != null){
		 IOUtils.closeQuietly(inputStream);
		}
	}
	logger.info("OUT::POST:://tenant/uploadImage/{cat}/{id}::uploadtenantImages::"+id+"::"+cat); 

}


public List<Resource> retriveIdproofImages(){
	
	return null;
	
}

public  InputStream retriveIdproofImage(Long id, String cat) throws IOException {
	 
	File dir = new File(tenantRootLocation+File.separator+id+File.separator+cat);
	if(dir.isDirectory()){
		File file = dir.listFiles()[0]; 
	    InputStream targetStream = new FileInputStream(file); 
       return targetStream;
	}
	
	return null;
}


}