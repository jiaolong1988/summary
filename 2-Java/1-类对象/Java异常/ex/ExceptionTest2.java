package test.java.ex;

/**
 * 
 * @ClassName:  ExceptionTest2   
 * @Description:TODO(异常跟踪)   
 * @author: jiaolong
 * @date:   2022年3月22日 上午11:59:00      
 * @Copyright:
 */
public class ExceptionTest2 {

	public void bid(String bidPrice) {	
		try {
			Double.parseDouble(bidPrice);			
		} catch (Exception e) {      //异常2
			//将异常对象放入异常链中，方便找原因。
			throw new RuntimeException("the bidPrice field must number!", e);	
		}			  
	}

	public static void main(String[] args) {
		//程序入口
		ExceptionTest2 t2 = new ExceptionTest2();
		
		try {
			t2.bid("df");
		} catch (Exception e) {    //异常1
			e.printStackTrace();			
			System.err.println("异常信息："+e.getMessage());
		}
	}

}
