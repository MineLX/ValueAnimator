package com.zyh.pro.animator.main.animators;

import com.zyh.pro.animator.main.animators.AnimatorBuilder.AnimatorListener;
import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimator;
import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Evaluator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Updater;

import java.util.List;
import java.util.function.Supplier;

public class GetterAnimator<T> implements Animator {

	private final Evaluator<T> evaluator;

	private final Supplier<T> startGetter;

	private final Supplier<T> endGetter;

	private final List<AnimatorListener> listeners;

	private final List<Updater<T>> updaters;

	private final int duration;

	private ValueAnimator animator;

	GetterAnimator(Evaluator<T> evaluator, Supplier<T> startGetter, Supplier<T> endGetter, List<AnimatorListener> listeners, List<Updater<T>> updaters,
	               int duration) {
		this.evaluator = evaluator;
		this.listeners = listeners;
		this.updaters = updaters;
		this.startGetter = startGetter;
		this.endGetter = endGetter;
		this.duration = duration;
	}

	@Override
	public void start() {
		ValueAnimatorBuilder builder = new ValueAnimatorBuilder();
		listeners.forEach(builder::addListener);

		animator = builder
				.setDuration(duration)
				.objectOrder(evaluator, startGetter.get(), endGetter.get(), updaters)
				.build();
		animator.start();
	}

	@Override
	public void stop() {
		if (!isRunning()) return;
		animator.stop();
	}

	@Override
	public boolean isRunning() {
		return animator != null && animator.isRunning();
	}
}
