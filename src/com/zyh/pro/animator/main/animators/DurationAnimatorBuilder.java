package com.zyh.pro.animator.main.animators;

public abstract class DurationAnimatorBuilder<Self extends DurationAnimatorBuilder<Self>>
		extends AnimatorBuilder<Self> {

	protected int duration;

	public Self setDuration(int duration) {
		this.duration = duration;
		return self();
	}

	public int getDuration() {
		return duration;
	}

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
			protected AnimatorBuilder self() {
				return this;
			}

			@Override
			public Animator build() {
				return Animator.empty();
			}
		};
	}
}
