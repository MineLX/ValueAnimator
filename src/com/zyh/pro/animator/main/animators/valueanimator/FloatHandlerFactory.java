package com.zyh.pro.animator.main.animators.valueanimator;

import com.zyh.pro.animator.main.animators.valueanimator.evaluations.FloatEvaluator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.FloatUpdater;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Interpolator;

import java.util.List;

public class FloatHandlerFactory extends UpdateHandlerFactory {

	private final List<FloatUpdater> updaters;

	private final float start;

	private final float end;

	FloatHandlerFactory(Interpolator interpolator, List<FloatUpdater> updaters, float start, float end) {
		super(interpolator);

		this.updaters = updaters;
		this.start = start;
		this.end = end;
	}

	public UpdateHandler createUpdateHandler() {
		return new FloatUpdateHandler(new FloatEvaluator(), interpolator, updaters, start, end);
	}
}
