package com.founder.tt;

@FkTag(age = 5)
@FkTag(name = "疯狂Java", age = 9)
//@FkTags({@FkTag(age=5),	@FkTag(name="疯狂Java" , age=9)})
public class FkTagTest {
	public static void main(String[] args) {
		Class<FkTagTest> clazz = FkTagTest.class;
		
		//使用Java 8新增的getDeclaredAnnotationsByType()方法获取 修饰FkTagTest类的多个@FkTag注解
		FkTag[] tags = clazz.getDeclaredAnnotationsByType(FkTag.class);
		// 遍历修饰FkTagTest类的多个@FkTag注解
		for (FkTag tag : tags) {
			System.out.println(tag.name() + "-->" + tag.age() + "  " + tag.name());
		}

		//使用传统的getDeclaredAnnotation()方法获取 修饰FkTagTest类的@FkTags注解
		FkTags container = clazz.getDeclaredAnnotation(FkTags.class);
		System.out.println(container.value()[0]);
	}
}
