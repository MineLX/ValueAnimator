package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.CompositeAnimator;
import com.zyh.pro.animator.main.animators.CompositeAnimatorBuilder;
import com.zyh.pro.animator.main.animators.GetterAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimatorBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CompositeTest {

	public static void main(String[] args) throws InterruptedException {
		new CompositeAnimatorBuilder()
				.with(new GetterAnimatorBuilder<>(CompositeTest::cloneCurrentBall, CompositeTest::createEnd, new Location.LocationEvaluator())
						.setDuration(1000)
						.addUpdater(System.out::println))
				.after(new ValueAnimatorBuilder()
						.setDuration(2000)
						.addListener(new TestAnimatorListener())
						.floatOrder(0, 500, System.out::println))
				.build().start();
	}

	private static Location createEnd() {
		return new Location(0, 0);
	}

	private static Location cloneCurrentBall() {
		return new Location(50, 100);
	}

	private static void stop_composite() throws InterruptedException {
		CompositeAnimator animator = getCompositeAnimator().build();
		animator.start();
		Thread.sleep(1000);
		animator.stop();
	}

	private static void simple_composite() {
		CompositeAnimator compositeAnimation = getCompositeAnimator().build();
		compositeAnimation.start();
	}

	static CompositeAnimatorBuilder getCompositeAnimator() {
		ValueAnimatorBuilder shorter =
				new ValueAnimatorBuilder().floatOrder(0f, 500f, x -> System.out.println("short: " + x));
		ValueAnimatorBuilder longer =
				new ValueAnimatorBuilder().setDuration(2000).floatOrder(0f, 500f, x -> System.out.println("longer: " + x));
		ValueAnimatorBuilder after =
				new ValueAnimatorBuilder().floatOrder(400f, 1000f, x1 -> System.out.println("after: " + x1));
		return new CompositeAnimatorBuilder()
				.with(shorter).with(longer)
				.after(after)
				.addListener(new TestAnimatorListener());
	}
}
