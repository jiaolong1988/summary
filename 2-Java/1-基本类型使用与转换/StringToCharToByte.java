package test.base;


import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringToCharToByte {
	
	public static void main(String[] args) throws Exception {
		String example = "j焦";
		System.out.println("测试信息："+example);
		System.out.println("=============");
		 
//      byte[] bytes = example.getBytes(StandardCharsets.UTF_8);
		byte[] bytes = getBytes(example.toCharArray());		
        for(byte b: bytes) {
        	 System.out.println("string -> bytes "+ Integer.toBinaryString(b)+" int:"+b);
        }       
        System.out.println("=============");
        
        //byte[] 转  String		
        String str = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("bytes -> String "+str);
        System.out.println("=============");
        
        
		//byte[] 转换为 chars
		char[] exampleChars = getChars(example.getBytes());//example.toCharArray();
		for(char c : exampleChars) {
			System.out.println("string -> chars "+c+" 二进制 " + Integer.toBinaryString(c)+" 二进制长度 "+Integer.toBinaryString(c).length() +" int:"+(int)c);
		}

	}

	//https://www.cnblogs.com/yaowen/p/9173443.html
	// char转byte 
	private static byte[] getBytes (char[] chars) {
	   Charset cs = Charset.forName ("UTF-8");//StandardCharsets.UTF_8 = Charset.forName ("UTF-8")
	   
	   CharBuffer cb = CharBuffer.allocate (chars.length);
	   cb.put (chars);
	   cb.flip ();
	   
	   ByteBuffer bb = cs.encode (cb);
	   return bb.array();
	 
	 }
	// byte转char
	 
	private static char[] getChars (byte[] bytes) {
	      Charset cs = Charset.forName ("UTF-8");
	      
	      ByteBuffer bb = ByteBuffer.allocate (bytes.length);
	      bb.put (bytes);
	      bb.flip ();
	      
	      CharBuffer cb = cs.decode (bb);
	  
	   return cb.array();
	}
	
	public static void test3() throws Exception{
		RandomAccessFile aFile = new RandomAccessFile("d:/t_archiv.sql", "rw");  
		FileChannel inChannel = aFile.getChannel();  
		  
		//create buffer with capacity of 48 bytes  
		ByteBuffer buf = ByteBuffer.allocate(48);  
		  
		int bytesRead = inChannel.read(buf); //read into buffer.  
		while (bytesRead != -1) {  
		  
		  buf.flip();  //make buffer ready for read  
		  
		  while(buf.hasRemaining()){  
		      System.out.print((char) buf.get()); // read 1 byte at a time  
		  }  
		  
		  buf.clear(); //make buffer ready for writing  
		  bytesRead = inChannel.read(buf);  
		}  
		aFile.close();  
	}
}
