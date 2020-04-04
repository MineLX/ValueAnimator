package com.zyh.pro.animator.main.animators.valueanimator.evaluations;

public class PowInterpolator implements Interpolator {
	@Override
	public float interpolate(float f) {
		return (float) Math.pow(f, 2);
	}
}
