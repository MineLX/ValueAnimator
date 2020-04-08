package com.zyh.pro.animator.main.animators;

import java.util.List;

public class ToggleAnimator implements Animator {

	private final List<AnimatorBuilder> toggleItems;

	private Animator currentAnimator;

	private int toggleIndex;

	ToggleAnimator(List<AnimatorBuilder> toggleItems) {
		this.toggleItems = toggleItems;
		toggleIndex = 0;
	}

	@Override
	public void start() {
		if (currentAnimator != null && currentAnimator.isRunning())
			currentAnimator.stop();

		currentAnimator = toggleItems.get(toggleIndex).build();
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
