package com.founder.tt;

@FkTag(age = 5)
@FkTag(name = "���Java", age = 9)
//@FkTags({@FkTag(age=5),	@FkTag(name="���Java" , age=9)})
public class FkTagTest {
	public static void main(String[] args) {
		Class<FkTagTest> clazz = FkTagTest.class;
		
		//ʹ��Java 8������getDeclaredAnnotationsByType()������ȡ ����FkTagTest��Ķ��@FkTagע��
		FkTag[] tags = clazz.getDeclaredAnnotationsByType(FkTag.class);
		// ��������FkTagTest��Ķ��@FkTagע��
		for (FkTag tag : tags) {
			System.out.println(tag.name() + "-->" + tag.age() + "  " + tag.name());
		}

		//ʹ�ô�ͳ��getDeclaredAnnotation()������ȡ ����FkTagTest���@FkTagsע��
		FkTags container = clazz.getDeclaredAnnotation(FkTags.class);
		System.out.println(container.value()[0]);
	}
}
