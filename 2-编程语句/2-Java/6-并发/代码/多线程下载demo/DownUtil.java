package com.founder.ntest.socket;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class DownUtil
{
	// 定义下载资源的路径
	private String path;
	// 指定所下载的文件的保存位置
	private String targetFile;
	// 定义需要使用多少线程下载资源
	private int threadNum;
	// 定义下载的线程对象
	private DownThread[] threads;
	// 定义下载的文件的总大小
	private int fileSize;
	//已下载文件信息
	private static List<String> cacheInfo = new ArrayList<>();	
	
	public DownUtil(String path, String targetFile, int threadNum)
	{
		this.path = path;
		this.threadNum = threadNum;
		// 初始化threads数组
		threads = new DownThread[threadNum];
		this.targetFile = targetFile;
	}

	public void download() throws Exception
	{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		conn.setRequestProperty(
			"Accept",
			"image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
			+ "application/x-shockwave-flash, application/xaml+xml, "
			+ "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
			+ "application/x-ms-application, application/vnd.ms-excel, "
			+ "application/vnd.ms-powerpoint, application/msword, */*");
		conn.setRequestProperty("Accept-Language", "zh-CN");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Connection", "Keep-Alive");
		// 得到文件大小
		fileSize = conn.getContentLength();
		System.out.println("文件大小："+fileSize);
		conn.disconnect();
		
		int currentPartSize = fileSize / threadNum + 1;
		RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
		// 设置本地文件的大小
		file.setLength(fileSize);
		file.close();
		
		List<String> downCache = getCache();
		if(downCache == null) {
			for (int i = 0; i < threadNum; i++)
			{
				// 计算每条线程的下载的开始位置
				int startPos = i * currentPartSize;
				// 每个线程使用一个RandomAccessFile进行下载
				RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
				// 定位该线程的下载位置
				currentPart.seek(startPos);
				// 创建下载线程
				threads[i] = new DownThread(startPos, currentPartSize, currentPart,0);
				// 启动下载线程
				threads[i].start();
			}
		}else {
			//断点续作
			for(int i=0; i<downCache.size(); i++) {
				String[] dInfo = downCache.get(i).split(",");
				int startPos = Integer.valueOf(dInfo[0]);
				int currentPartSize1 = Integer.valueOf(dInfo[1]);
				int length = Integer.valueOf(dInfo[2]);
				
				// 每个线程使用一个RandomAccessFile进行下载
				RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
				// 定位该线程的下载位置
				currentPart.seek(startPos);
				// 创建下载线程
				threads[i] = new DownThread(startPos, currentPartSize1, currentPart,length);
				// 启动下载线程
				threads[i].start();
			}
		}
	}

	/**
	 * 获取下载的缓存信息
	 * @return
	 */
	public  List<String> getCache(){
		List<String> list = null;
		File f = new File("startPas.txt");
		if(f.exists()) {
			try {
				list = FileUtils.readLines(f);
				System.out.println("startPas.txt:"+list);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	private int cacheSize(List<String> list) {
		int cacheSize = 0;
		for(String c: list){
			String sigleCache = c.split(",")[2];
			cacheSize+=Integer.valueOf(sigleCache);
		}
		return cacheSize;
	}
	
	
	// 获取下载的完成百分比
	public double getCompleteRate()
	{
		// 统计多条线程已经下载的总大小
		int sumSize = 0;

		for (int i = 0; i < threadNum; i++)
		{
			//已下载长度
			int used = threads[i].length;
			//未下载长度
		    int remaining = threads[i].currentPartSize - used;
		    //当前位置
		    int currentLocal = threads[i].startPos + used;
    
		    //输入字段信息：当前要写入数据的位置，当前还有多少数据未写入，当前已写入多少数据
		    String info = "";
		    if(remaining<0) {
		    	 //未下载的大小不能是负数，表示已下载完毕
		    	info = used + "," + 0+","+used;
		    }else {
		    	info = currentLocal + "," + remaining+","+used;
		    }
		    cacheInfo.add(info);
			//统计已经下载大小
			sumSize += used;
		}
		
		try {
			FileUtils.writeLines(new File("startPas.txt"), cacheInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			cacheInfo.clear();
		}
		
		// 返回已经完成的百分比
		return sumSize * 1.0 / fileSize;
	}

	private class DownThread extends Thread
	{
		// 当前线程的下载位置
		public int startPos;
		// 定义当前线程负责下载的文件大小
		public int currentPartSize;
		// 当前线程需要下载的文件块
		private RandomAccessFile currentPart;
		// 定义已经该线程已下载的字节数
		public int length;
		
		public DownThread(int startPos, int currentPartSize,
			RandomAccessFile currentPart, int length)
		{
			this.startPos = startPos;
			this.currentPartSize = currentPartSize;
			this.currentPart = currentPart;
			this.length = length;
		}

		@Override
		public void run()
		{
			try
			{
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection)url
					.openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestMethod("GET");
				conn.setRequestProperty(
					"Accept",
					"image/gif, image/jpeg, image/pjpeg, image/pjpeg, "
					+ "application/x-shockwave-flash, application/xaml+xml, "
					+ "application/vnd.ms-xpsdocument, application/x-ms-xbap, "
					+ "application/x-ms-application, application/vnd.ms-excel, "
					+ "application/vnd.ms-powerpoint, application/msword, */*");
				conn.setRequestProperty("Accept-Language", "zh-CN");
				conn.setRequestProperty("Charset", "UTF-8");
				InputStream inStream = conn.getInputStream();
				// 跳过startPos个字节，表明该线程只下载自己负责哪部分文件。
				inStream.skip(this.startPos);
				
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				// 读取网络数据，并写入本地文件
				while (length < currentPartSize
					&& (hasRead = inStream.read(buffer)) != -1)
				{
						currentPart.write(buffer, 0, hasRead);
						// 累计该线程下载的总大小
						length += hasRead;
				}
				currentPart.close();
				inStream.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	

}
