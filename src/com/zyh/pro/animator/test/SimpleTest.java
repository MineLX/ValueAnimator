package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.Animator;
import com.zyh.pro.animator.main.animators.Animators;
import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimatorBuilder;

public class SimpleTest {

	public static void main(String[] args) throws InterruptedException {
		test_object();
	}

	private static void test_simpleAnimator() throws InterruptedException {
		Animator animator = Animators.justDoIt(() -> System.out.println("no! no god! no god please no!"));
		animator.start();
		Thread.sleep(2000);
		animator.stop();
	}

	private static void test_object() {
		Animators.valueObject(new Location.LocationEvaluator(), new Location(0, 50), new Location(50, 0), 1000, System.out::println)
				.start();
	}

	private static void test_float() {
		new ValueAnimatorBuilder()
				.addListener(new TestAnimatorListener())
				.floatOrder(0f, 100f, System.out::println)
				.build().start();
	}

}
