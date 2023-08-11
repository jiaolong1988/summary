package com.founder.encrypt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LinkedHashMap 顺序保存数据
 * @author jiaolong
 * @date 2023-03-03 11:20:33
 */
public class CollectionSort {

	public static void main(String[] args) throws Exception {
		mapSort1();
		System.out.println("");
		
		mapSort2();
		System.out.println("");

	}
    private static void mapSort1() {

    	Map<String, Integer> map = new HashMap<>();
    	// insert value
        map.put("a", 1);
        map.put("aa", 3);
        map.put("ab", 5);
        map.put("ba", 7);
        map.put("bb", 8);
        map.put("aac", 23);
        map.put("bac", 8);
        
        HashMap<String, Integer> x = 
        		   map.entrySet()
                   .stream()
                    //对集合进行排序
                   .sorted((i1, i2) -> i1.getKey().compareTo(i2.getKey()))
                   .collect(
                		   //返回map集合
                		   Collectors.toMap(
                				   Map.Entry::getKey,
                				   Map.Entry::getValue,
                				   (z1, z2) -> z1, 
                				   LinkedHashMap::new)
                		   );

        // Show Sorted HashMap
        for (Map.Entry<String, Integer> start : x.entrySet()) {
            System.out.println("Key=" + start.getKey() + ",	Value="   + start.getValue());
        }
    }
   
    private static void mapSort2() {
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("d", 1);
	    map.put("c", 3);
	    map.put("b", 2);
	    map.put("a", 4);
	    
		//这里将map.entrySet()转换成list,map.entry表示Map中的一个实体（一个key-value对）
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        
        //遍历输出，foreach方式
        for(Map.Entry<String, Integer> mapping: list){ 
               System.out.println(mapping.getKey()+":"+mapping.getValue()); 
        }
        
        System.out.println("=============");
        
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
			@Override
			public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getValue()-o2.getValue();
			}            
        });
        
        //遍历输出，foreach方式
        for(Map.Entry<String, Integer> mapping: list){ 
               System.out.println(mapping.getKey()+":"+mapping.getValue()); 
         }
	}
	
	

}
