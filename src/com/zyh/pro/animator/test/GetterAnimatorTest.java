package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.Animator;
import com.zyh.pro.animator.main.animators.GetterAnimator;
import com.zyh.pro.animator.main.animators.GetterAnimatorBuilder;

public class GetterAnimatorTest {
	private static Location location = new Location(200, 200);

	public static void main(String[] args) {
		Animator animator = new GetterAnimatorBuilder<>(GetterAnimatorTest::currentClone, () -> new Location(50, 50), new Location.LocationEvaluator())
				.addUpdater(System.out::println)
				.setDuration(4000)
				.build();
		animator.start();
	}

	private static Location currentClone() {
		return new Location(location.x, location.y);
	}
}
