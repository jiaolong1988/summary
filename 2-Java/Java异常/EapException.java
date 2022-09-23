package com.icbc.eap.ssapi;

public class EapException extends Exception implements ErrorCodes {
	private static final long serialVersionUID = 1L;
	public static final Integer FILENOTEXISTERROR = new Integer(-63);
	private int errCode;

	public EapException(int errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public EapException(int errCode, String message, Throwable cause) {
		super(message, cause);
		this.errCode = errCode;
	}

	public int getErrorCode() {
		return this.errCode;
	}

	public String getMessage() {
		return this.netError(this.errCode, super.getMessage());
	}

	public String getMessageString() {
		return this.getErrMsg(this.errCode);
	}

	public String toString() {
		return this.netError(this.errCode, super.toString());
	}

	private String netError(int result, String msg) {
		String strtmp = "error code: " + result + ". ";
		strtmp = strtmp + this.getErrMsg(result);
		strtmp = strtmp + msg;
		return strtmp;
	}

	private String getErrMsg(int result) {
		String strtmp;
		switch (result) {
			case -761 :
				strtmp = "单次Socket通信中出现不同文件夹（刻录命令）";
				break;
			case -760 :
				strtmp = "DAM缓存容量不足（刻录命令）";
				break;
			case -752 :
				strtmp = "ISP更新全部失败错误（刻录完成通知command）";
				break;
			case -751 :
				strtmp = "Index缓存容量不足（刻录命令）";
				break;
			case -750 :
				strtmp = "可以刻录的光盘匣不足（刻录命令）";
				break;
			case -743 :
				strtmp = "未发现要查询的文件（查询命令）";
				break;
			case -742 :
				strtmp = "接收的消息格式异常（所用命令）";
				break;
			case -741 :
				strtmp = "文件夹创建失败（刻录错误）";
				break;
			case -740 :
				strtmp = "Volume创建失败（刻录错误）";
				break;
			case -739 :
				strtmp = "应用号未分配（刻录命令）";
				break;
			case -738 :
				strtmp = "刻录命令retry文件名不正确（刻录命令）";
				break;
			case -737 :
				strtmp = "向DAM传送数据时报错（刻录命令）";
				break;
			case -736 :
				strtmp = "md5值异常（刻录任务）";
				break;
			case -735 :
				strtmp = "DABC缓存容量不足（刻录命令）";
				break;
			case -734 :
				strtmp = "查询动作错误（查询命令）";
				break;
			case -732 :
				strtmp = "文件夹已存在（刻录命令）";
				break;
			case -703 :
				strtmp = "数据准备中，排队查询较多，请15分钟后再查询";
				break;
			case -702 :
				strtmp = "数据准备中，请2分钟之后再查询。";
				break;
			case -701 :
				strtmp = "数据已经离线，已经通知管理员操作，请半个工作日后再查询";
				break;
			case -386 :
				strtmp = "WConsole地址未登记";
				break;
			case -385 :
				strtmp = "更新模块程序失败";
				break;
			case -381 :
				strtmp = "流媒体批次中含有不止一个文件";
				break;
			case -380 :
				strtmp = "流媒体RM存储非LRM模式";
				break;
			case -379 :
				strtmp = "STRUCT成员不存在";
				break;
			case -378 :
				strtmp = "批次所在文件块只读";
				break;
			case -371 :
				strtmp = "不支持此交易";
				break;
			case -366 :
				strtmp = "抠小图失败";
				break;
			case -352 :
				strtmp = "数据库操作异常";
				break;
			case -351 :
				strtmp = "批次状态错误";
				break;
			case -350 :
				strtmp = "批次状态不允许本操作";
				break;
			case -348 :
				strtmp = "被查询批次页码不存在";
				break;
			case -343 :
				strtmp = "RM无此批次";
				break;
			case -342 :
				strtmp = "数据库无此批次";
				break;
			case -335 :
				strtmp = "待上传影像文件不存在";
				break;
			case -334 :
				strtmp = "批次索引文件解析错误";
				break;
			case -333 :
				strtmp = "替换服务器上的批次索引文件失败";
				break;
			case -332 :
				strtmp = "指定批次在服务器上已存在";
				break;
			case -331 :
				strtmp = "待上传索引文件不存在";
				break;
			case -322 :
				strtmp = "服务器返回超时";
				break;
			case -320 :
				strtmp = "与服务器建立连接失败";
				break;
			case -310 :
				strtmp = "输入参数格式错误";
				break;
			case -63 :
				strtmp = "影像文件不存在";
				break;
			case -62 :
				strtmp = "解析业务信息文件错误";
				break;
			case -61 :
				strtmp = "业务信息文件不存在";
				break;
			case -60 :
				strtmp = "业务信息版本已同步";
				break;
			case -57 :
				strtmp = "批次无指定页码号";
				break;
			case -56 :
				strtmp = "指定批次不存在文档部件属性";
				break;
			case -55 :
				strtmp = "用于查询的属性名在被查询的项类型中不存在";
				break;
			case -54 :
				strtmp = "向CM检入批次失败";
				break;
			case -53 :
				strtmp = "从CM中检出批次失败";
				break;
			case -52 :
				strtmp = "更新待处理批次列表文件失败";
				break;
			case -51 :
				strtmp = "批次状态错误";
				break;
			case -50 :
				strtmp = "批次状态不允许本操作";
				break;
			case -49 :
				strtmp = "向CM查询出错";
				break;
			case -48 :
				strtmp = "被查询批次已离线或不存在";
				break;
			case -47 :
				strtmp = "批次已向上级服务器提交(异步查询)";
				break;
			case -46 :
				strtmp = "异步查询正在处理,请稍后再查";
				break;
			case -45 :
				strtmp = "指定批次已被处理";
				break;
			case -44 :
				strtmp = "CM中无符合查询条件的批次";
				break;
			case -43 :
				strtmp = "指定级数的服务器无此批次或批次状态不可操作";
				break;
			case -42 :
				strtmp = "本级服务器无此批次，不存在上级服务器";
				break;
			case -41 :
				strtmp = "本级服务器无此批次，存在上级服务器(只在只读情况下上溯到上一级服务器查询)";
				break;
			case -40 :
				strtmp = "获取批次失败";
				break;
			case -34 :
				strtmp = "批次索引文件解析错误";
				break;
			case -33 :
				strtmp = "替换服务器上的批次索引文件失败";
				break;
			case -32 :
				strtmp = "指定批次在服务器上已存在";
				break;
			case -31 :
				strtmp = "待提交索引文件不存在";
				break;
			case -30 :
				strtmp = "待提交批次不存在";
				break;
			case -24 :
				strtmp = "向服务器发送文件或文件夹失败";
				break;
			case -23 :
				strtmp = "从服务器接收文件或文件夹失败";
				break;
			case -22 :
				strtmp = "服务器返回超时";
				break;
			case -21 :
				strtmp = "交易失败";
				break;
			case -20 :
				strtmp = "与服务器建立连接失败";
				break;
			case -10 :
				strtmp = "输入参数格式错误";
				break;
			default :
				strtmp = "错误码未定义";
		}

		return strtmp;
	}
}