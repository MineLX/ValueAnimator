package com.zyh.pro.animator.main.animators;

import java.util.ArrayList;
import java.util.List;

public class ToggleAnimatorBuilder extends DurationAnimatorBuilder<ToggleAnimatorBuilder> {

	private final List<AnimatorBuilder> toggleItems;

	public ToggleAnimatorBuilder() {
		toggleItems = new ArrayList<>();
	}

	public ToggleAnimatorBuilder addToggle(AnimatorBuilder toggleItem) {
		toggleItems.add(toggleItem);
		return this;
	}

	@Override
	public ToggleAnimator build() {
		return new ToggleAnimator(toggleItems);
	}

	@Override
	protected ToggleAnimatorBuilder self() {
		return this;
	}
}
