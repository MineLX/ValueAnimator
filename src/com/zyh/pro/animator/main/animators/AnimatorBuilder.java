package com.zyh.pro.animator.main.animators;

public interface AnimatorBuilder {

	int DEF_FPS = 60;

	AnimatorBuilder addListener(AnimatorListener listener);

	Animator build();

	interface AnimatorListener {
		void onAnimationStart();

		void onAnimationEnd();
	}
}
