package com.zyh.pro.animator.main.animators;

import java.util.ArrayList;
import java.util.List;

public class SimpleAnimatorBuilder extends AnimatorBuilder {

	private final List<AnimatorListener> listeners; // FIXME 2020/4/3  wait for me!!! duplicated listeners initializations

	private final List<Runnable> updaters;

	public SimpleAnimatorBuilder() {
		listeners = new ArrayList<>();
		updaters = new ArrayList<>();
	}

	@Override
	public SimpleAnimatorBuilder addListener(AnimatorBuilder.AnimatorListener listener) {
		listeners.add(listener);
		return this;
	}

	@Override
	protected AnimatorBuilder self() {
		return this;
	}

	public SimpleAnimatorBuilder addUpdater(Runnable updater) {
		updaters.add(updater);
		return this;
	}

	@Override
	public SimpleAnimator build() {
		return new SimpleAnimator(listeners, updaters);
	}
}
