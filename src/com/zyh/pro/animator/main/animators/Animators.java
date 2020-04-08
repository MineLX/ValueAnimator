package com.zyh.pro.animator.main.animators;

import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.DefaultInterpolator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Evaluator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.FloatUpdater;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Updater;

public class Animators {

	private Animators() {
	}

	public static Animator valueFloat(float start, float end, int duration, FloatUpdater updater) {
		ValueAnimatorBuilder builder = new ValueAnimatorBuilder()
				.setDuration(duration)
				.setInterpolator(new DefaultInterpolator())
				.floatOrder(start, end, updater);
		return builder.build();
	}

	public static <T> Animator valueObject(Evaluator<T> evaluator, T start, T end, int duration, Updater<T> updater) {
		ValueAnimatorBuilder builder = new ValueAnimatorBuilder()
				.setDuration(duration)
				.setInterpolator(new DefaultInterpolator())
				.objectOrder(evaluator, start, end, updater);
		return builder.build();
	}

	public static Animator justDoIt(Runnable it) {
		return new SimpleAnimatorBuilder()
				.addUpdater(it)
				.build();
	}
}
