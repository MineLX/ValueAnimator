package com.zyh.pro.animator.main.animators;

import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Evaluator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Updater;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GetterAnimatorBuilder<T> extends DurationAnimatorBuilder {

	private final List<Updater<T>> updaters;

	private final Supplier<T> startGetter;

	private final Supplier<T> endGetter;

	private final Evaluator<T> evaluator;

	public GetterAnimatorBuilder(Supplier<T> startGetter, Supplier<T> endGetter, Evaluator<T> evaluator) {
		this.startGetter = startGetter;
		this.endGetter = endGetter;
		this.evaluator = evaluator;

		updaters = new ArrayList<>();
	}

	public GetterAnimatorBuilder addUpdater(Updater<T> updater) {
		updaters.add(updater);
		return this;
	}

	@Override
	protected AnimatorBuilder self() {
		return this;
	}

	@Override
	public GetterAnimator<T> build() {
		return new GetterAnimator<>(evaluator, startGetter, endGetter, updaters, duration);
	}
}
