package com.founder.shcpe.datacompare.util;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class PropertiesUtil {
	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);
	private Properties props = new Properties();
	private String propertyFilePath;

	public PropertiesUtil(String propertyFilePath) {
		this.propertyFilePath = propertyFilePath;

		try(FileInputStream fis = new FileInputStream(propertyFilePath);) {			
			props.load(fis);
		} catch (IOException e) {
			logger.error(e);
		}	
	}
	
	public void reLoadProp() {
		try(OutputStream out = new FileOutputStream(propertyFilePath);) {			
			props.store(out, "Project configuration file");
		} catch (IOException e) {
			logger.error(e);
		}	
	}

	public String getVal(String propName) {
		return props.getProperty(propName);	
	}

	public void setVal(String propName, String newValue) {
		props.setProperty(propName, newValue);
	}
	
	public Properties getProps() {
		return props;
	}

	
	public static void main(String[] args) throws IOException {

//		for(int i=0;i<10;i++) {
//			String f = "D:\\test\\aaa.txt";
//			String bak = "D:\\test\\aaa-bak.txt";
//			FileUtils.copyFile(new File(bak), new File(f));
//			
//			PropertiesUtil pu = new PropertiesUtil(f);
//			
//			System.out.println(pu.getProps().keySet());		
//			System.out.println("--"+pu.getVal("aa"));
//			pu.setVal("bb", "0");
//		
//			pu.getProps().remove("bb");
//			pu.reLoadProp();
//			//new File(f).delete();
//		}
	
	}
}
