package jh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * list 转换为map数组
 * @author jiaolong
 * @date 2023-03-01 03:28:54
 */
public class ListToMap {
	class User {
		private long id;
		private String name;

		User(long id, String name) {
			this.id = id;
			this.name = name;
		}

		public long getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}
	
	public static void main(String[] args) {
		List<User> users = new ArrayList<>();
		//创建内部类对象
		ListToMap.User user1 = new ListToMap().new User(1, "zhenzidan");
		users.add(user1);
		users.add(new ListToMap().new User(2, "chenglong"));

		Map<Long, String> x = users.stream().collect(Collectors.toMap(User::getId, User::getName, (u1, u2) -> u1));
		System.out.println(x);
	}

}
