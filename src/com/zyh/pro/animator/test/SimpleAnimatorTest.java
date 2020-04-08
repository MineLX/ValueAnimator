package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.SimpleAnimator;
import com.zyh.pro.animator.main.animators.SimpleAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimator;
import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.loopmodes.LoopMode;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleAnimatorTest {

	public static void main(String[] args) throws InterruptedException {
		valueAnimator();
	}

	private static void valueAnimator() throws InterruptedException {
		ValueAnimator animator = new ValueAnimatorBuilder()
				.setLoopMode(LoopMode.infinity_reversed())
				.floatOrder(100, 500, System.out::println)
				.build();
		animator.start();
		Thread.sleep(500);
		assertThat(animator.isRunning(), is(true));
		System.out.println("request stop...");
		animator.stop();
		Thread.sleep(500);
		assertThat(animator.isRunning(), is(false));
	}

	public static void simpleAnimator() throws InterruptedException {
		SimpleAnimator animator = new SimpleAnimatorBuilder()
				.addListener(new TestAnimatorListener())
				.addUpdater(() -> System.out.println("update"))
				.build();

		animator.start();
		Thread.sleep(2000);
		assertThat(animator.isRunning(), is(true));
		animator.stop();
		animator.stop();
		animator.stop();
		Thread.sleep(200);
		assertThat(animator.isRunning(), is(false));
	}
}
