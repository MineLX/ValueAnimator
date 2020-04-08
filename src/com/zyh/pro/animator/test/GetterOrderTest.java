package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimatorBuilder;

public class GetterOrderTest {
	public static void main(String[] args) {
		new ValueAnimatorBuilder()
				.getterOrder(new Location.LocationEvaluator(), () -> new Location(50, 250), () -> new Location(250, 0), System.out::println)
				.build().start();
	}
}
