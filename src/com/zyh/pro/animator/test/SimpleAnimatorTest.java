package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.SimpleAnimator;
import com.zyh.pro.animator.main.animators.SimpleAnimatorBuilder;

public class SimpleAnimatorTest {
	public static void main(String[] args) throws InterruptedException {
		SimpleAnimator animator = new SimpleAnimatorBuilder()
				.addListener(new TestAnimatorListener())
				.addUpdater(() -> System.out.println("update"))
				.build();

		animator.start();
		Thread.sleep(2000);
		animator.stop();
	}

}
