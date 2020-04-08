package com.zyh.pro.animator.main.animators.valueanimator;

import com.zyh.pro.animator.main.animators.DurationAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.*;
import com.zyh.pro.animator.main.animators.valueanimator.loopmodes.LoopMode;

import java.util.List;

import static java.util.Collections.singletonList;

public class ValueAnimatorBuilder extends DurationAnimatorBuilder<ValueAnimatorBuilder> {

	private static int DEF_FPS = 60;

	private ValueAnimator result;

	private Interpolator interpolator;

	private ValueAnimator.FramesCallbackFactory loopMode;

	public ValueAnimatorBuilder() {
		interpolator = new DefaultInterpolator();
		duration = 1000;
		loopMode = LoopMode.normal();
	}

	@Override
	protected ValueAnimatorBuilder self() {
		return this;
	}

	public ValueAnimatorBuilder setInterpolator(Interpolator interpolator) {
		this.interpolator = interpolator;
		return this;
	}

	public ValueAnimatorBuilder setLoopMode(ValueAnimator.FramesCallbackFactory loopMode) {
		this.loopMode = loopMode;
		return this;
	}

	public ValueAnimatorBuilder floatOrder(float start, float end, FloatUpdater updaters) { // FIXME 2020/4/4  wait for me!!!  it obliges that method should be the terminator of chain invocations
		return floatOrder(start, end, singletonList(updaters));
	}

	public <T> ValueAnimatorBuilder objectOrder(Evaluator<T> evaluator, T start, T end, Updater<T> updaters) {
		return objectOrder(evaluator, start, end, singletonList(updaters));
	}

	public ValueAnimatorBuilder floatOrder(float start, float end, List<FloatUpdater> updaters) {
		FloatUpdateHandler handler = new FloatUpdateHandler(interpolator, updaters, start, end);
		result = new ValueAnimator(loopMode, handler, listeners, DEF_FPS, duration);
		return this;
	}

	public <T> ValueAnimatorBuilder objectOrder(Evaluator<T> evaluator, T start, T end, List<Updater<T>> updaters) {
		ObjectUpdateHandler<T> handler = new ObjectUpdateHandler<>(evaluator, interpolator, updaters, start, end);
		result = new ValueAnimator(loopMode, handler, listeners, DEF_FPS, duration);
		return this;
	}

	@Override
	public ValueAnimator build() {
		return result;
	}
}
