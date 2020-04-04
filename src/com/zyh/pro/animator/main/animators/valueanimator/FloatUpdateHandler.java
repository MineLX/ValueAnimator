package com.zyh.pro.animator.main.animators.valueanimator;

import com.zyh.pro.animator.main.animators.valueanimator.evaluations.FloatEvaluator;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.FloatUpdater;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Interpolator;

import java.util.List;

public class FloatUpdateHandler extends UpdateHandler {

	private final FloatEvaluator evaluator;

	private final List<FloatUpdater> updaters;

	private final float start;

	private final float end;

	FloatUpdateHandler(FloatEvaluator evaluator, Interpolator interpolator, List<FloatUpdater> updaters,
	                   float start, float end) {
		super(interpolator);

		this.evaluator = evaluator;
		this.updaters = updaters;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void onUpdate(float rateAt) {
		float evaluateValue = evaluator.evaluate(rateAt, start, end);

		for (FloatUpdater updater : updaters)
			updater.update(evaluateValue);
	}
}
