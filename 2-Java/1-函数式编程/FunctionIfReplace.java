package com.founder.jt;

import java.util.function.Consumer;

/**
 * https://mp.weixin.qq.com/s/3V_GtJjna5b8wy5GcWnQ0g
 * <p>
 * Function�ӿ� ����if...else
 * 
 * @author jiaolong
 *
 */
public class FunctionIfReplace {
	public static void main(String[] args) {

		// 1.�����׳��쳣��if
		VUtils.isTure(false).throwMessage("11111111111");

		// 2.����if��֧����
		VUtils.isTureOrFalse(false).trueOrFalseHandle(() -> {
			System.out.println("true , statr...");
		}, () -> {
			System.out.println("false, end...");
		});

		// 3.�������ִֵ�����Ѳ���������ִ�л��ڿյĲ���
		VUtils.isBlankOrNoBlank("hello").presentOrElseHandle(System.out::println, () -> {
			System.out.println("���ַ���");
		});

	}
}

@FunctionalInterface
interface ThrowExceptionFunction {
	/**
	 * �׳��쳣��Ϣ
	 *
	 * @param message �쳣��Ϣ
	 * @return void
	 **/
	void throwMessage(String message);
}

@FunctionalInterface
interface BranchHandle {
	/**
	 * ��֧����
	 *
	 * @param trueHandle  ΪtrueʱҪ���еĲ���
	 * @param falseHandle ΪfalseʱҪ���еĲ���
	 * @return void
	 **/
	void trueOrFalseHandle(Runnable trueHandle, Runnable falseHandle);
}

interface PresentOrElseHandler<T extends Object> {

	/**
	 * ֵ��Ϊ��ʱִ�����Ѳ��� ֵΪ��ʱִ�������Ĳ���
	 * 
	 * @param action      ֵ��Ϊ��ʱ��ִ�е����Ѳ���
	 * @param emptyAction ֵΪ��ʱ��ִ�еĲ���
	 * @return void
	 **/
	void presentOrElseHandle(Consumer<? super T> action, Runnable emptyAction);

}

class VUtils {

	public static ThrowExceptionFunction isTure(boolean b) {

		ThrowExceptionFunction tef = (errorMessage) -> {
			if (b) {
				throw new RuntimeException(errorMessage);
			}
		};

		return tef;
	}

	public static BranchHandle isTureOrFalse(boolean b) {
		return (trueHandle, falseHandle) -> {
			if (b) {
				trueHandle.run();
			} else {
				falseHandle.run();
			}
		};
	}

	public static PresentOrElseHandler<?> isBlankOrNoBlank(String str) {

		return (consumer, runnable) -> {
			if (str == null || str.length() == 0) {
				runnable.run();
			} else {
				consumer.accept(str);
			}
		};
	}

}
