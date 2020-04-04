package com.zyh.pro.animator.main.animators;

public interface Animator {
	void start();

	void stop();

	static Animator empty() {
		return new Animator() {
			@Override
			public void start() {
			}

			@Override
			public void stop() {
			}
		};
	}
}
