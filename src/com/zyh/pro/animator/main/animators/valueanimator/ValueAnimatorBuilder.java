package com.zyh.pro.animator.main.animators.valueanimator;

import com.zyh.pro.animator.main.animators.DurationAnimatorBuilder;
import com.zyh.pro.animator.main.animators.valueanimator.evaluations.*;
import com.zyh.pro.animator.main.animators.valueanimator.loopmodes.LoopMode;

import java.util.ArrayList;
import java.util.List;

public class ValueAnimatorBuilder implements DurationAnimatorBuilder {

	private ValueAnimator result;

	private List<AnimatorListener> listeners;

	private Interpolator interpolator;

	private int duration;

	private ValueAnimator.FramesCallbackFactory loopMode;

	public ValueAnimatorBuilder() {
		listeners = new ArrayList<>();
		interpolator = new DefaultInterpolator();
		duration = DEF_DURATION;
		loopMode = LoopMode.normal();
	}

	@Override
	public ValueAnimatorBuilder addListener(AnimatorListener listener) {
		listeners.add(listener);
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

	@Override
	public ValueAnimatorBuilder setDuration(int duration) {
		this.duration = duration;
		return this;
	}

	@Override
	public int getDuration() {
		return duration;
	}

	public ValueAnimatorBuilder floatOrder(float start, float end, FloatUpdater updater) { // FIXME 2020/4/4  wait for me!!!  it obliges that method should be the terminator of chain invocations
		FloatHandlerFactory factory = new FloatHandlerFactory(interpolator, List.of(updater), start, end);
		result = new ValueAnimator(loopMode, factory, listeners, DEF_FPS, duration);
		return this;
	}

	public <T> ValueAnimatorBuilder objectOrder(Evaluator<T> evaluator, T start, T end, Updater<T> updater) {
		ObjectHandlerFactory<T> factory = new ObjectHandlerFactory<>(evaluator, interpolator, List.of(updater), start, end);
		result = new ValueAnimator(loopMode, factory, listeners, DEF_FPS, duration);
		return this;
	}

	@Override
	public ValueAnimator build() {
		return result;
	}
}
