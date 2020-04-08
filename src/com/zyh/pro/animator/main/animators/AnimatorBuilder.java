package com.zyh.pro.animator.main.animators;

import java.util.ArrayList;
import java.util.List;

public abstract class AnimatorBuilder<Self extends AnimatorBuilder<Self>> {

	protected final List<AnimatorListener> listeners;

	AnimatorBuilder() {
		listeners = new ArrayList<>();
	}

	public Self addListener(AnimatorListener listener) {
		listeners.add(listener);
		return self();
	}

	protected abstract Self self();

	public abstract Animator build();

	public interface AnimatorListener {
		void onAnimationStart();

		void onAnimationEnd();
	}
}
