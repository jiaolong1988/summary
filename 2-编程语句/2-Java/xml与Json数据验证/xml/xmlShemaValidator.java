
public static String validateXml(String xsdPath, String xmlPath) {
		String returnMsg=ErrNoConst.OPERATE_SUCCESS;
		InputStream in = null;
		try {
			// 建立schema工厂
			SchemaFactory schemaFactory = SchemaFactory.newInstance(PubConst.INDEX_XMLSCHEMA);
			Schema schema = schemaFactory.newSchema(StringUtils.class.getResource(xsdPath));
			Validator validator = schema.newValidator();
			// 得到验证的数据源
			in = new FileInputStream(xmlPath);
			InputSource inputSource = new InputSource(in);
			Source source = new SAXSource(inputSource);
			validator.validate(source);
		} catch (Exception e) {
			//add by yanghn 20210219 增加了空指针处理逻辑
			returnMsg = e!=null?e.getMessage():"Exception." + ",fileName:" + new File(xmlPath).getName();
			logger.error(returnMsg);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e1) {

			}
		}
		if(logger.isDebugEnabled()){logger.debug("validateXml end");}
		return returnMsg;
		
	}