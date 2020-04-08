package com.zyh.pro.animator.main.animators.valueanimator;

import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Evaluator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Interpolator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Updater;

import java.util.List;
import java.util.function.Supplier;

public class GetterHandler<T> extends UpdateHandler {

	private final List<Updater<T>> updaters;

	private final Supplier<T> start;

	private final Supplier<T> end;

	private final Evaluator<T> evaluator;

	private boolean isInit;

	private T startValue;

	private T endValue;

	GetterHandler(Evaluator<T> evaluator, Interpolator interpolator, List<Updater<T>> updaters, Supplier<T> start, Supplier<T> end) {
		super(interpolator);
		this.updaters = updaters;
		this.start = start;
		this.end = end;
		this.evaluator = evaluator;
		isInit = true;
	}

	@Override
	protected void onUpdate(float rateAt) {
		if (isInit) {
			startValue = start.get();
			endValue = end.get();
			isInit = false;
		}
		T evaluateValue = evaluator.evaluate(rateAt, startValue, endValue);
		for (Updater<T> updater : updaters)
			updater.update(evaluateValue);
	}
}
