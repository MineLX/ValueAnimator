package com.zyh.pro.animator.main.animators;

import com.zyh.pro.animator.main.animators.AnimatorBuilder.AnimatorListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.Comparator.comparingInt;

public class CompositeAnimator implements Animator {

	private final List<Animator> runningAnimators;

	private final ReentrantLock runningAnimatorsLock;

	private final AtomicInteger levelIndex;

	private final List<List<DurationAnimatorBuilder>> animators;

	private final List<AnimatorListener> listeners;

	CompositeAnimator(List<List<DurationAnimatorBuilder>> animators, List<AnimatorListener> listeners) {
		this.animators = animators;
		this.listeners = listeners;

		levelIndex = new AtomicInteger();
		runningAnimators = new ArrayList<>();
		runningAnimatorsLock = new ReentrantLock();
	}

	@Override
	public void start() {
		setupListeners();
		onStart();
		startLevel(0);
	}

	@Override
	public void stop() {
		levelIndex.set(animators.size());
		runningAnimatorsLock.lock();
		runningAnimators.forEach(Animator::stop);
		runningAnimatorsLock.unlock();
	}

	private void onStart() {
		listeners.forEach(AnimatorListener::onAnimationStart);
	}

	private void onEnd() {
		listeners.forEach(AnimatorListener::onAnimationEnd);
	}

	private void startLevel(int index) {
		runningAnimatorsLock.lock();
		runningAnimators.clear();
		for (DurationAnimatorBuilder builder : animators.get(index)) {
			Animator animator = builder.build();
			runningAnimators.add(animator);
			animator.start();
		}
		runningAnimatorsLock.unlock();
	}

	private void setupListeners() {
		StepListener listener = new StepListener();

		for (List<DurationAnimatorBuilder> builders : animators)
			builders.stream()
					.max(comparingInt(DurationAnimatorBuilder::getDuration))
					.orElseGet(DurationAnimatorBuilder::empty)
					.addListener(listener);
	}
	private class StepListener implements AnimatorListener {
		@Override
		public void onAnimationStart() {
		}

		@Override
		public void onAnimationEnd() {
			if (levelIndex.incrementAndGet() >= animators.size()) {
				onEnd();
				return;
			}
			startLevel(levelIndex.get());
		}
	}
}
