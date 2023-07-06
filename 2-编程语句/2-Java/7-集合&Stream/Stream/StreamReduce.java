package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamReduce {
	public static void main(String[] args) {

		List<Integer> numList = Arrays.asList(1,2,3,4,5);
		Optional<Integer> result = numList.stream().reduce((a,b) -> a + b );
		System.out.println(result.get());
		
		List<Integer> numList1 = Arrays.asList(1,2,3,4,5);
		Integer result1 = numList1.stream().reduce(1, (a,b) -> a + b );
		System.out.println(result1);
		
		
		List<Integer> numList2 = Arrays.asList(1, 2, 3, 4, 5);
        String result2 = numList2.stream().reduce("__", (a, b) -> a += String.valueOf(b), (x, t) -> x+t);
        System.out.println(result2);
        
		List<Integer> numList3 = Arrays.asList(1, 2, 3, 4, 5);
        String result3 = numList3.parallelStream().reduce(
        		"_", 
        		(a, b) -> {
        			//第一次合并
        			System.out.println("a,b："+a+" --> "+b);
        			return a += String.valueOf(b);
        		}, 
        		(x, t) -> {
        			//第二次合并
        			System.out.println("x,t："+x+" --> "+t);
        			return x+t;
        		}
        );
        System.out.println(result3);

   
        
	}

	
}
