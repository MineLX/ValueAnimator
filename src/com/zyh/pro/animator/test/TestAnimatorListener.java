package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.AnimatorBuilder;

public class TestAnimatorListener implements AnimatorBuilder.AnimatorListener {
	@Override
	public void onAnimationStart() {
		System.out.println("onAnimationStart");
	}

	@Override
	public void onAnimationEnd() {
		System.out.println("onAnimationEnd");
	}
}
