package com.zyh.pro.animator.main.animators;

import com.zyh.pro.animator.main.choreographer.Choreographer;
import com.zyh.pro.animator.main.animators.AnimatorBuilder.AnimatorListener;
import com.zyh.pro.animator.main.choreographer.StateCallback;
import com.zyh.pro.animator.main.looper.Looper;
import com.zyh.pro.animator.main.looper.Message;

import java.util.List;

public class SimpleAnimator implements Animator {

	private static final int DEF_FPS = 60;

	private final MyCallback callback;

	private final Choreographer task;

	private final Looper looper;

	SimpleAnimator(List<AnimatorListener> listeners, List<Runnable> updaters) {
		looper = new Looper(message -> updaters.forEach(Runnable::run));
		callback = new MyCallback(listeners);
		task = new Choreographer(DEF_FPS, callback);
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

	@Override
	public boolean isRunning() {
		return callback.isRunning();
	}

	private class MyCallback extends StateCallback {
		private final List<AnimatorListener> listeners;

		private MyCallback(List<AnimatorListener> listeners) {
			this.listeners = listeners;
		}

		@Override
		public void onNotify(Choreographer choreographer) {
			looper.getQueue().enter(Message.get());
		}

		@Override
		public void onStartNotify() {
			listeners.forEach(AnimatorListener::onAnimationStart);
		}

		@Override
		public void onEndNotify() {
			listeners.forEach(AnimatorListener::onAnimationEnd);
		}
	}
}
