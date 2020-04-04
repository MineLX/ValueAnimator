package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.Animator;
import com.zyh.pro.animator.main.animators.Animators;

public class StopValueAnimatorTest {
	public static void main(String[] args) throws InterruptedException {
		Animator animator = Animators.valueFloat(0, 100f, 5000, System.out::println);
		animator.start();
		Thread.sleep(1000);
		animator.stop();
	}
}
