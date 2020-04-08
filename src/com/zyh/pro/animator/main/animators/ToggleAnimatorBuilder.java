package com.zyh.pro.animator.main.animators;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ToggleAnimatorBuilder extends DurationAnimatorBuilder<ToggleAnimatorBuilder> {

	private final List<Supplier<AnimatorBuilder>> toggleItems;

	public ToggleAnimatorBuilder() {
		toggleItems = new ArrayList<>();
	}

	public ToggleAnimatorBuilder addToggle(Supplier<AnimatorBuilder> toggleItem) {
		toggleItems.add(toggleItem);
		return this;
	}

	@Override
	public Animator build() {
		return new ToggleAnimator(toggleItems);
	}

	@Override
	protected ToggleAnimatorBuilder self() {
		return this;
	}
}
