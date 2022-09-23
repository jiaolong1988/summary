package lambda;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * 通过stream方式将hashMap转换为排序的Map
 * @author 		jiaolong
 * @date 		2022-09-21 11:08:47
 */
public class HashMapSortTest {
	// unsorted 
    static Map<String, Integer> map = new HashMap<>();

    public static void sort(){
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
	public static void main(String[] args) throws Exception {
		// insert value
        map.put("a", 1);
        map.put("aa", 3);
        map.put("ab", 5);
        map.put("ba", 7);
        map.put("bb", 8);
        map.put("aac", 23);
        map.put("bac", 8);
       
        sort();
	}

}
