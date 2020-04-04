package com.zyh.pro.animator.main.animators;

import com.zyh.pro.animator.main.common.Choreographer;
import com.zyh.pro.animator.main.animators.AnimatorBuilder.AnimatorListener;
import com.zyh.pro.animator.main.looper.Looper;
import com.zyh.pro.animator.main.looper.Message;

import java.util.List;

public class SimpleAnimator implements Animator {

	private static final int DEF_FPS = 60;

	private final Choreographer task;

	private final Looper looper;

	SimpleAnimator(List<AnimatorListener> listeners, List<Runnable> updaters) {
		looper = new Looper(message -> updaters.forEach(Runnable::run));
		task = new Choreographer(DEF_FPS, new Choreographer.Callback() {
			@Override
			public void onNotify(Choreographer choreographer) {
				looper.getQueue().enter(Message.get());
			}

			@Override
			public void onStart() {
				listeners.forEach(AnimatorListener::onAnimationStart);
			}

			@Override
			public void onEnd() {
				listeners.forEach(AnimatorListener::onAnimationEnd);
			}
		});
	}

	@Override
	public void start() {
		looper.start();
		task.start();
	}

	@Override
	public void stop() {
		looper.interrupt();
		task.stop();
	}
}
