package com.founder.shcpe.datacompare.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Properties工具类，支持顺序存储
 * 
 * @author 		jiaolong
 * @date 		2022-09-19 02:40:03
 */
public class OrderedPropertiesUtil{
	private static final Logger logger = Logger.getLogger(OrderedPropertiesUtil.class);
	
	private Properties prop = new OrderedProperties();
	private String propertyFilePath;
	
	public OrderedPropertiesUtil(String propertyFilePath) {
		this.propertyFilePath = propertyFilePath;
		
		try(FileInputStream fis = new FileInputStream(propertyFilePath);) {			
			prop.load(fis);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public void setVal(String propName, String newValue) {
		prop.setProperty(propName, newValue);
	}
	
	public String getVal(String propName) {
		return prop.getProperty(propName);	
	}
	
	public Properties getProps() {
		return prop;
	}
	
	public void reLoadProp() {
		try(OutputStream out = new FileOutputStream(propertyFilePath);) {			
			prop.store(out, "Project configuration file");
		} catch (IOException e) {
			logger.error(e);
		}	
	}
	
//	public static void main(String[] args) throws IOException {
//		for(int i=0;i<10;i++) {
//			String f = "D:\\test\\aaa.txt";
//			String bak = "D:\\test\\aaa-bak.txt";
//			FileUtils.copyFile(new File(bak), new File(f));
//			
//			OrderedPropertiesUtil opu = new OrderedPropertiesUtil(f);
//			System.out.println(opu.getProps().keySet());		
//			System.out.println("--"+opu.getVal("aa"));
//			System.out.println(opu.getProps().values());
//			
//			opu.setVal("11", "aa");
//			opu.setVal("22", "bb");
//			
//			opu.getProps().remove("bb");
//			opu.reLoadProp();
//	    }
//	}
	
	
}

/**
 * 閲嶅啓Properties,鏀寔椤哄簭瀛樺偍
 * 
 * @author 		jiaolong
 * @date 		2022-09-19 02:20:48
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