package com.zyh.pro.animator.main.animators.valueanimator;

import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Evaluator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Interpolator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Updater;

import java.util.List;

public class ObjectHandlerFactory<T> extends UpdateHandlerFactory {

	private final Evaluator<T> evaluator;

	private final List<Updater<T>> updaters;

	private final T start;

	private final T end;

	ObjectHandlerFactory(Evaluator<T> evaluator, Interpolator interpolator, List<Updater<T>> updaters, T start, T end) {
		super(interpolator);

		this.evaluator = evaluator;
		this.updaters = updaters;
		this.start = start;
		this.end = end;
	}

	@Override
	public UpdateHandler createUpdateHandler() {
		return new ObjectUpdateHandler<>(evaluator, interpolator, updaters, start, end);
	}

}
