package com.boc.icms.dbimport.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Properties�����֧࣬��˳��洢
 * @author jiaolong
 * @date 2023-11-16 04:24:12
 */
public class OrderedPropertiesUtil{
	private static final Logger logger = Logger.getLogger(OrderedPropertiesUtil.class);
	
	private OrderedProperties prop ;
	private String propertyFilePath ;
	
	public OrderedPropertiesUtil(String propertyFilePath) {
		this.propertyFilePath = propertyFilePath;	
		this.prop = new OrderedProperties();
	}
		
	/**
	 * ����Properties �ļ�
	 * @param values
	 * @return
	 */
	public synchronized boolean createProperFile(Map<String, String> values) {
		for (Map.Entry<String, String> entry : values.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			prop.setProperty(key, value);
		}

		return outPropFile();
	}
	
	
	/**
	 * �޸��������ļ���
	 * @param propName
	 * @param newValue
	 */
	public synchronized boolean setValToFile(String propName, String newValue) {
		loadFile();
		prop.setProperty(propName, newValue);
		outPropFile();
		
		return getVal(propName).equals(newValue);
	}
	
	/**
	 * ��ȡָ�����Ե�ֵ
	 * @param propName
	 * @return
	 */
	public synchronized String getVal(String propName) {
		
		loadFile();
		String val = prop.getProperty(propName);
		if(val == null) {			
			logger.error("=====================>>>>>>>>>>>>>>>>>> read interrupt file info is null. attrName:"+propName +"  filePath:"+propertyFilePath);
				   
			for (Map.Entry<Object, Object> entry :prop.entrySet()) {
				Object key = entry.getKey();
				Object value = entry.getValue();
				
				logger.error("getVal ["+key+"] ==> key:"+key+" value:"+value);
			}
			return "";
		}
		
		return val;	
	}
	
	/**
	 * ��ȡָ�����Ե�ֵ
	 * @param propName
	 * @return
	 */
	public synchronized Map<String,String> getAllVal() {
		
		if(!loadFile()) {
			return Collections.emptyMap(); 
		}
		
		Map<String,String> result = new HashMap<>();		
		for (Map.Entry<Object, Object> entry :prop.entrySet()) {
			String key = (String)entry.getKey();
			String value =(String) entry.getValue();
			
			result.put(key, value);
		}
		return result;	
	}

	private synchronized boolean outPropFile() {
		boolean flag = false;
		try(OutputStream out = new FileOutputStream(propertyFilePath);) {			
			prop.store(out, "Project configuration file");
			flag = true;
		} catch (IOException e) {
			logger.error(" Interrupt file write fail, filePath:"+propertyFilePath,e);
		}
		return flag;
	}	
	
	
	/**
	 * ��ȡ�ж��ļ���
 	 * @return true�������ļ��ɹ��� false:�ļ�����ʧ��
	 */
	private boolean loadFile() {	
		if(new File(propertyFilePath).exists()) {
			try(FileInputStream fis = new FileInputStream(propertyFilePath)) {			
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
 * @date 2023-11-16 04:24:37
 */
class OrderedProperties extends Properties {

    private static final long serialVersionUID = 4710927773256743817L;

    private final LinkedHashSet<Object> keys = new LinkedHashSet<>();

    @Override
    public Enumeration<Object> keys() {
        return Collections.<Object> enumeration(keys);
    }

    @Override
    public synchronized Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

    @Override
    public Set<Object> keySet() {
        return keys;
    }

    @Override
	public Collection<Object> values() {
    	 Set<Object> set = new LinkedHashSet<>();
    	 
    	 for (Object key : this.keys) {
    		set.add(this.getProperty((String) key));
    	 }
    	 
		return set;
	}

	@Override
	public synchronized Object remove(Object key) {
    	keys.remove(key);
		return super.remove(key);
	}

	@Override
    public Set<String> stringPropertyNames() {
        Set<String> set = new LinkedHashSet<>();

        for (Object key : this.keys) {
            set.add((String) key);
        }

        return set;
    }

	@Override
	public synchronized int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(keys);
		return result;
	}

	@Override
	public synchronized boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderedProperties other = (OrderedProperties) obj;
		return Objects.equals(keys, other.keys);
	}
	
}