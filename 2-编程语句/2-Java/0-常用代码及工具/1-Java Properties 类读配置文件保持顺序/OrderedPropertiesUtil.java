package com.boc.icms.makelist.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * Properties工具类，支持顺序存储
 * 
 * @author 		jiaolong
 * @date 		2022-09-19 02:40:03
 */
public class OrderedPropertiesUtil{
	private static final Logger logger = Logger.getLogger(OrderedPropertiesUtil.class);
	
	public Properties prop = new OrderedProperties();
	private String propertyFilePath ;
	
	public OrderedPropertiesUtil(String propertyFilePath) {
		this.propertyFilePath = propertyFilePath;	
	}
		
	/**
	 * 创建Properties 文件
	 * @param values
	 * @return
	 */
	public boolean createProperFile(Map<String,String> values) {
		for(String key :values.keySet()) {
			prop.setProperty(key, values.get(key));
		}
		
		logger.debug("configPath --> "+propertyFilePath);
		for(Object key:prop.keySet()) {
			logger.debug("create config.flag info ==> key:"+key+" value:"+prop.get(key) );
		}
		
		return outPropFile();
	}
	
	
	/**
	 * 更新属性至文件中
	 * @param propName
	 * @param newValue
	 */
	public boolean setValToFile(String propName, String newValue) {
		loadFile();
		prop.setProperty(propName, newValue);
		boolean flag = outPropFile();
		
		logger.debug("outPropFile:  ==>"+flag);	
		for(Object key:prop.keySet()) {
			logger.debug("setValToFile ["+propName+"] value:"+newValue+" ==> key:"+key+" value:"+prop.get(key));
		}
		
		while(true) {
			String fileNewValue = getVal(propName);
			if(fileNewValue.equals(newValue)) {
				break;
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {}
		}
	
		return true;
	}
	
	/**
	 * 获取指定属性的值
	 * @param propName
	 * @return
	 */
	public String getVal(String propName) {
		
		loadFile();
		for(Object key:prop.keySet()) {
			logger.debug("getVal ["+propName+"] ==> key:"+key+" value:"+prop.get(key));
		}
		String val = prop.getProperty(propName);
		if(val == null) {
			logger.error("=====================>>>>>>>>>>>>>>>>>> get config.flag file info is null. attrName:"+propName +"  filePath:"+propertyFilePath);
		    System.exit(0);
		}
		
		return val;	
	}
	


	private boolean outPropFile() {
		boolean flag = false;
		try(OutputStream out = new FileOutputStream(propertyFilePath);) {			
			prop.store(out, "Project configuration file");
			flag = true;
		} catch (IOException e) {
			logger.error(e);
		}
		return flag;
	}	
	
	private boolean loadFile() {	
		if(new File(propertyFilePath).exists()) {
			try(FileInputStream fis = new FileInputStream(propertyFilePath);) {			
				prop.load(fis);
				return true;
			} catch (IOException e) {
				logger.error("load file error."+propertyFilePath, e);
			}
		}else {
			logger.error("properties file not exist."+propertyFilePath);
		}
		
		return false;
	}
	
}

/**
 * 
 * @author jiaolong
 * @date 2022-11-09 09:30:16
 */
class OrderedProperties extends Properties {

    private static final long serialVersionUID = 4710927773256743817L;

    private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

    @Override
    public Enumeration<Object> keys() {
        return Collections.<Object> enumeration(keys);
    }

    @Override
    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

    @Override
    public Set<Object> keySet() {
        return keys;
    }

    @Override
	public Collection<Object> values() {
    	 Set<Object> set = new LinkedHashSet<Object>();
    	 
    	 for (Object key : this.keys) {
    		set.add(this.getProperty((String) key));
    	 }
    	 
		return set;
	}

	@Override
	public  Object remove(Object key) {
    	keys.remove(key);
		return super.remove(key);
	}

	@Override
    public Set<String> stringPropertyNames() {
        Set<String> set = new LinkedHashSet<String>();

        for (Object key : this.keys) {
            set.add((String) key);
        }

        return set;
    }
}