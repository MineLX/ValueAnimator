package com.zyh.pro.animator.main.animators.valueanimator.evaluations;

public class FloatEvaluator {
	public float evaluate(float f, float start, float end) {
		return start + f * (end - start);
	}
}
