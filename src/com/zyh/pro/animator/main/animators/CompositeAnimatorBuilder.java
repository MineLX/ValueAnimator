package com.zyh.pro.animator.main.animators;

import com.zyh.pro.animator.main.common.Composition;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class CompositeAnimatorBuilder implements DurationAnimatorBuilder {

	private final List<AnimatorListener> listeners;

	private final Composition<DurationAnimatorBuilder> builders;

	public CompositeAnimatorBuilder() {
		builders = new Composition<>();
		listeners = new ArrayList<>();
	}

	public CompositeAnimatorBuilder with(DurationAnimatorBuilder with) {
		builders.with(with);
		return this;
	}

	public CompositeAnimatorBuilder after(DurationAnimatorBuilder after) {
		builders.after(after);
		return this;
	}

	@Override
	public CompositeAnimatorBuilder addListener(AnimatorListener listener) {
		listeners.add(listener);
		return this;
	}

	@Override
	public CompositeAnimatorBuilder setDuration(int newDuration) {
		float rate = 1f * newDuration / getDuration();
		for (DurationAnimatorBuilder builder : builders)
			builder.setDuration((int) (builder.getDuration() * rate));
		return this;
	}

	@Override
	public int getDuration() {
		return builders.toList().stream()
				.map(this::getMaxOnDuration)
				.mapToInt(DurationAnimatorBuilder::getDuration)
				.reduce(0, Integer::sum);
	}

	private DurationAnimatorBuilder getMaxOnDuration(List<DurationAnimatorBuilder> item) {
		return item.stream()
				.max(comparingInt(DurationAnimatorBuilder::getDuration))
				.orElseGet(DurationAnimatorBuilder::empty);
	}

	@Override
	public CompositeAnimator build() {
		return new CompositeAnimator(builders.toList(), listeners);
	}
}
