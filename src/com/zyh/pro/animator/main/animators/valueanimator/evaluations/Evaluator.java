package com.zyh.pro.animator.main.animators.valueanimator.evaluations;

public interface Evaluator<T> {
	T evaluate(float f, T start, T end);
}
