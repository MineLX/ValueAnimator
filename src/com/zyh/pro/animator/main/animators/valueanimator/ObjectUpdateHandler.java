package com.zyh.pro.animator.main.animators.valueanimator;

import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Evaluator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Interpolator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Updater;

import java.util.List;

public class ObjectUpdateHandler<T> extends UpdateHandler {

	private final Evaluator<T> evaluator;

	private final List<Updater<T>> updaters;

	private final T start;

	private final T end;

	ObjectUpdateHandler(Evaluator<T> evaluator, Interpolator interpolator, List<Updater<T>> updaters,
	                    T start, T end) {
		super(interpolator);

		this.evaluator = evaluator;
		this.updaters = updaters;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void onUpdate(float rateAt) {
		T evaluateValue = evaluator.evaluate(rateAt, start, end);

		for (Updater<T> updater : updaters)
			updater.update(evaluateValue);
	}
}
