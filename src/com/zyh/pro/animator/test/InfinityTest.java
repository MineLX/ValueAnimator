package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimator;
import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.loopmodes.LoopMode;

public class InfinityTest {

	public static void main(String[] args) throws InterruptedException {
		test_inverse();
	}

	private static void test_reverse_infinity() {
		startWithLoopMode(LoopMode.reversed_infinity(-1));
	}

	private static void test_infinity_inverse() {
		startWithLoopMode(LoopMode.infinity_reversed(2));
	}

	private static void test_inverse() {
		startWithLoopMode(LoopMode.reversed());
	}

	private static void test_infinity() {
		startWithLoopMode(LoopMode.infinity(2));
	}

	private static void test_normal() {
		startWithLoopMode(LoopMode.normal());
	}

	private static void startWithLoopMode(ValueAnimator.FramesCallbackFactory loopMode) {
		ValueAnimator animator = new ValueAnimatorBuilder()
				.setLoopMode(loopMode)
				.addListener(new TestAnimatorListener())
				.floatOrder(0f, 100f, System.out::println)
				.build();
		animator.start();
	}
}
