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
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class DownUtil
{
	// ����������Դ��·��
	private String path;
	// ָ�������ص��ļ��ı���λ��
	private String targetFile;
	// ������Ҫʹ�ö����߳�������Դ
	private int threadNum;
	// �������ص��̶߳���
	private DownThread[] threads;
	// �������ص��ļ����ܴ�С
	private int fileSize;
	//�������ļ���Ϣ
	private static List<String> cacheInfo = new ArrayList<>();	
	
	public DownUtil(String path, String targetFile, int threadNum)
	{
		this.path = path;
		this.threadNum = threadNum;
		// ��ʼ��threads����
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
		// �õ��ļ���С
		fileSize = conn.getContentLength();
		System.out.println("�ļ���С��"+fileSize);
		conn.disconnect();
		
		int currentPartSize = fileSize / threadNum + 1;
		RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
		// ���ñ����ļ��Ĵ�С
		file.setLength(fileSize);
		file.close();
		
		List<String> downCache = getCache();
		if(downCache == null) {
			for (int i = 0; i < threadNum; i++)
			{
				// ����ÿ���̵߳����صĿ�ʼλ��
				int startPos = i * currentPartSize;
				// ÿ���߳�ʹ��һ��RandomAccessFile��������
				RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
				// ��λ���̵߳�����λ��
				currentPart.seek(startPos);
				// ���������߳�
				threads[i] = new DownThread(startPos, currentPartSize, currentPart,0);
				// ���������߳�
				threads[i].start();
			}
		}else {
			//�ϵ�����
			for(int i=0; i<downCache.size(); i++) {
				String[] dInfo = downCache.get(i).split(",");
				int startPos = Integer.valueOf(dInfo[0]);
				int currentPartSize1 = Integer.valueOf(dInfo[1]);
				int length = Integer.valueOf(dInfo[2]);
				
				// ÿ���߳�ʹ��һ��RandomAccessFile��������
				RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
				// ��λ���̵߳�����λ��
				currentPart.seek(startPos);
				// ���������߳�
				threads[i] = new DownThread(startPos, currentPartSize1, currentPart,length);
				// ���������߳�
				threads[i].start();
			}
		}
	}

	/**
	 * ��ȡ���صĻ�����Ϣ
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
	
	
	// ��ȡ���ص���ɰٷֱ�
	public double getCompleteRate()
	{
		// ͳ�ƶ����߳��Ѿ����ص��ܴ�С
		int sumSize = 0;

		for (int i = 0; i < threadNum; i++)
		{
			//�����س���
			int used = threads[i].length;
			//δ���س���
		    int remaining = threads[i].currentPartSize - used;
		    //��ǰλ��
		    int currentLocal = threads[i].startPos + used;
    
		    //�����ֶ���Ϣ����ǰҪд�����ݵ�λ�ã���ǰ���ж�������δд�룬��ǰ��д���������
		    String info = "";
		    if(remaining<0) {
		    	 //δ���صĴ�С�����Ǹ�������ʾ���������
		    	info = used + "," + 0+","+used;
		    }else {
		    	info = currentLocal + "," + remaining+","+used;
		    }
		    cacheInfo.add(info);
			//ͳ���Ѿ����ش�С
			sumSize += used;
		}
		
		try {
			FileUtils.writeLines(new File("startPas.txt"), cacheInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			cacheInfo.clear();
		}
		
		// �����Ѿ���ɵİٷֱ�
		return sumSize * 1.0 / fileSize;
	}

	private class DownThread extends Thread
	{
		// ��ǰ�̵߳�����λ��
		public int startPos;
		// ���嵱ǰ�̸߳������ص��ļ���С
		public int currentPartSize;
		// ��ǰ�߳���Ҫ���ص��ļ���
		private RandomAccessFile currentPart;
		// �����Ѿ����߳������ص��ֽ���
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
				// ����startPos���ֽڣ��������߳�ֻ�����Լ������Ĳ����ļ���
				inStream.skip(this.startPos);
				
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				// ��ȡ�������ݣ���д�뱾���ļ�
				while (length < currentPartSize
					&& (hasRead = inStream.read(buffer)) != -1)
				{
						currentPart.write(buffer, 0, hasRead);
						// �ۼƸ��߳����ص��ܴ�С
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
