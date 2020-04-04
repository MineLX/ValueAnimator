package com.zyh.pro.animator.main.animators.valueanimator.evaluations;

public class DefaultInterpolator implements Interpolator {
	private static DefaultInterpolator instance;
	
	@Override
	public float interpolate(float f) {
		return f;
	}
	
	public static DefaultInterpolator getInstance() {
		return instance == null ? (instance = new DefaultInterpolator()) : instance;
	}
}
