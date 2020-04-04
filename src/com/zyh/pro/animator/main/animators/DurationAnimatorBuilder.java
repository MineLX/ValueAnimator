package com.zyh.pro.animator.main.animators;

public interface DurationAnimatorBuilder extends AnimatorBuilder {

	int DEF_DURATION = 1000;

	DurationAnimatorBuilder setDuration(int duration);

	int getDuration();

	static DurationAnimatorBuilder empty() {
		return new DurationAnimatorBuilder() {
			@Override
			public DurationAnimatorBuilder setDuration(int duration) {
				return this;
			}

			@Override
			public int getDuration() {
				return 0;
			}

			@Override
			public DurationAnimatorBuilder addListener(AnimatorListener listener) {
				return this;
			}

			@Override
			public Animator build() {
				return Animator.empty();
			}
		};
	}
}
