package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.Animator;
import com.zyh.pro.animator.main.animators.Animators;
import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Evaluator;

public class SimpleTest {

	public static void main(String[] args) throws InterruptedException {
		test_float();
	}

	private static void test_simpleAnimator() throws InterruptedException {
		Animator animator = Animators.justDoIt(() -> System.out.println("no! no god! no god please no!"));
		animator.start();
		Thread.sleep(2000);
		animator.stop();
	}

	private static void test_object() {
		Animators.valueObject(new LocationEvaluator(), new Location(0, 50), new Location(50, 0), 1000, System.out::println)
				.start();
	}

	private static void test_float() {
		new ValueAnimatorBuilder()
				.addListener(new TestAnimatorListener())
				.floatOrder(0f, 100f, System.out::println)
				.build().start();
	}

	private static final class Location {
		int x;
		int y;

		private Location(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Location(" + x + " , " + y + ")";
		}
	}

	private static final class LocationEvaluator implements Evaluator<Location> {
		final Location location = new Location(0, 0);

		@Override
		public Location evaluate(float f, Location start, Location end) {
			location.x = convert(f, start.x, end.x);
			location.y = convert(f, start.y, end.y);
			return location;
		}

		private int convert(float f, int start, int end) {
			return (int) (start + (end - start) * f);
		}
	}
}
