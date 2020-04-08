package com.zyh.pro.animator.main.animators;

import java.util.List;
import java.util.function.Supplier;

public class ToggleAnimator implements Animator {

	private final List<Supplier<AnimatorBuilder>> toggleItems;

	private Animator currentAnimator;

	private int toggleIndex;

	ToggleAnimator(List<Supplier<AnimatorBuilder>> toggleItems) {
		this.toggleItems = toggleItems;
		toggleIndex = 0;
	}

	@Override
	public void start() {
		if (currentAnimator != null && currentAnimator.isRunning())
			currentAnimator.stop();

		currentAnimator = toggleItems.get(toggleIndex).get().build();
		currentAnimator.start();
		nextToggle();
	}

	private void nextToggle() {
		toggleIndex ++;
		toggleIndex %= toggleItems.size();
	}

	@Override
	public void stop() {
		currentAnimator.stop();
	}

	@Override
	public boolean isRunning() {
		return currentAnimator.isRunning();
	}
}
