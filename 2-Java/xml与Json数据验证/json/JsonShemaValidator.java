package com.founder.fcms.http.util;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.founder.fcms.core.service.constant.ErrNoConst;

/**
 * ClassName: JsonShemaValidator <br/>
 * Function:json格式的数据校验工具类.<br/>
 * @author jiaq
 * @version 
 * @since 2020年9月2日.下午3:49:31
 */
public class JsonShemaValidator {

	private static Logger logger = Logger.getLogger(JsonShemaValidator.class);
    private static final String jsonSchemaPath = "/index.json";

    public static String validateJson(String sourceJson){
    	if(logger.isDebugEnabled()){logger.debug("into validatorJson method");}
    	String ret=null;
    	InputStream in=null;
    	try {
    		if(sourceJson == null || "".equals(sourceJson)){
    			ret="json data is empty.";
        	}else{
        		in = JsonShemaValidator.class.getResourceAsStream(jsonSchemaPath);
            	JSONObject schemaObj = new JSONObject(new JSONTokener(in));
            	JSONObject sourceJsonObj = new JSONObject(sourceJson);
            	Schema schema = SchemaLoader.load(schemaObj);
            	schema.validate(sourceJsonObj);
            	ret=ErrNoConst.OPERATE_SUCCESS;
        	}
    	} catch (Exception e) {
    		StringBuffer errMsg = new StringBuffer("failed to validate json.");
    		if(e!=null){
    			errMsg.append(e.getMessage());
	    		if(e instanceof ValidationException){
	    			List<String> allMessages = ((ValidationException)e).getAllMessages();
	    			for(String message:allMessages){
	    				errMsg.append(message+".");
	    			}
	    		}
    		}
    		ret =errMsg.toString();
    		logger.error(ret);
    	}finally{
    		try{if(in != null)in.close();}catch(Exception e1){}
    		if(logger.isDebugEnabled()){logger.debug("end to validatorJson method."+ret);}
    	}
    	return ret;
    }

}
