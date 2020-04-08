package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.Animator;
import com.zyh.pro.animator.main.animators.Animators;
import com.zyh.pro.animator.main.animators.ToggleAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Evaluator;

public class ToggleAnimatorTest {

	private static class Rectangle {
		int x;

		private Rectangle(int x) {
			this.x = x;
		}

		@Override
		public String toString() {
			return "Rectangle(" + x + ")";
		}
	}

	private static Rectangle rectangle = new Rectangle(500);

	public static void main(String[] args) throws InterruptedException {
		ToggleAnimatorBuilder builder = new ToggleAnimatorBuilder();

		builder.addToggle(() -> new ValueAnimatorBuilder()
						.setDuration(2000)
						.objectOrder(new RectEvaluator(), cloneCurrent(), new Rectangle(0), rectangle1 -> rectangle = rectangle1))
				.addToggle(() -> new ValueAnimatorBuilder()
						.setDuration(2000)
						.objectOrder(new RectEvaluator(), cloneCurrent(), new Rectangle(500), rectangle1 -> rectangle = rectangle1));
		Animator animator = builder.build();

		Animators.justDoIt(() -> System.out.println(rectangle)).start();

		animator.start();
		Thread.sleep(500);
		animator.start();
		Thread.sleep(700);
		animator.start();
	}

	private static Rectangle cloneCurrent() {
		System.out.println("cloneCurrent = " + rectangle);
		return new Rectangle(rectangle.x);
	}

	private static final class RectEvaluator implements Evaluator<Rectangle> {
		private final Rectangle result = new Rectangle(0);

		@Override
		public Rectangle evaluate(float f, Rectangle start, Rectangle end) {
			result.x = convert(start.x, end.x, f);
			return result;
		}

		private int convert(int start, int end, float f) {
			return (int) (start + (end - start) * f);
		}
	}
}
