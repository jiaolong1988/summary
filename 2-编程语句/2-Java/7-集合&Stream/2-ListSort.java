package sort;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ��List���ݽ�������
 * https://blog.csdn.net/lzxlfly/article/details/102878760
 * @author jiaolong
 * @date 2023-08-11 02:28:16
 */
public class ListSort {
		
	public static List<String> getTestData1(){
		List<String> list = new ArrayList<>();
		list.add("temp_encrypt_add_202204161416");
		list.add("temp_encrypt_add_202204161415");
		list.add("temp_encrypt_add_202204161617");
	    
   
        //���������foreach��ʽ
        for(String mapping: list){ 
               System.out.println(mapping); 
        }
        
        System.out.println("=============");
        
        return list;
	}
	
	public static List<DataInfo> getTestData2(){
		DataInfo order1 = new DataInfo("1101", "2019-11-03 00:30:31");
		DataInfo order2 = new DataInfo("1102", "2019-11-03 00:30:32");
		DataInfo order3 = new DataInfo("1103", "2019-11-03 00:30:33");
		DataInfo order4 = new DataInfo("1104", "2019-11-03 00:30:34");
		DataInfo order5 = new DataInfo("1105", "2019-11-03 00:30:35");
		
		List<DataInfo> orders = new ArrayList<>();
		orders.add(order2);
		orders.add(order1);
		orders.add(order4);
		orders.add(order3);
		orders.add(order5);
		System.out.println("------------����ǰ-----------------");
		for (DataInfo order : orders) {
			System.out.println("orderNum=" + order.getOrderNum() + ",payTime=" + order.getPayTime());
		}
		
		return orders;
	}
	
	private static void ListSort1Collections() {
		List<String> list = getTestData1();
        
        //Ȼ��ͨ���Ƚ�����ʵ������
        Collections.sort(list,new Comparator<String>() {
            //��������
			@Override
			public int compare(String o1, String o2) {
				String[] o1info = o1.split("_");				
				String[] o2info = o2.split("_");
				
				String val1 = o1info[o1info.length-1];
				String val2 = o2info[o1info.length-1];
							
				
				BigInteger o1val = new BigInteger(val1);
				BigInteger o2val = new BigInteger(val2);
				
				return o1val.compareTo(o2val);
			}            
        });
        
        //���������foreach��ʽ
        for(String mapping: list){ 
            System.out.println(mapping); 
        }
        
	}
	
	private static void ListSort2Sort() {	
		List<DataInfo> orders = getTestData2();
		
        //������Date��String��Long���͵����ڶ�������������ת��
		orders.sort((t1, t2) -> t2.getPayTime().compareTo(t1.getPayTime()));
		
		System.out.println("------------�����-----------------");
		for (DataInfo order : orders) {
			System.out.println("orderNum=" + order.getOrderNum() + ",payTime=" + order.getPayTime());
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		ListSort1Collections();
		ListSort2Sort();
	}
}


class DataInfo {
	/**
	 * ������
	 */
    private String orderNum;
    
    /**
	 * ����ʱ��
	 */
    private String payTime;
    
	public DataInfo(String orderNum, String payTime) {
		this.orderNum = orderNum;
		this.payTime = payTime;	
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
}