package com.zyh.pro.animator.main.animators.valueanimator;

import com.zyh.pro.animator.main.animators.Animator;
import com.zyh.pro.animator.main.animators.AnimatorBuilder.AnimatorListener;
import com.zyh.pro.animator.main.choreographer.Frames.FrameAccepter;
import com.zyh.pro.animator.main.choreographer.StateCallback;
import com.zyh.pro.animator.main.animators.valueanimator.loopmodes.Evaluation;
import com.zyh.pro.animator.main.choreographer.Frames;
import com.zyh.pro.animator.main.choreographer.Choreographer;
import com.zyh.pro.animator.main.looper.Looper;

import java.util.List;

import static com.zyh.pro.animator.main.choreographer.Frames.getAllFrames;

public class ValueAnimator implements Animator {

	private static final int STATE_READY = 0;

	private static final int STATE_RUNNING = 1;

	private int state;

	private final StateCallback callback;

	private final Choreographer choreographer;

	private final Looper looper;

	ValueAnimator(FramesCallbackFactory callbackFactory, UpdateHandler updateHandler, List<AnimatorListener> animatorListeners,
	              int fps, int duration) {
		looper = new Looper(updateHandler);

		FrameAccepter accepter =
				new Evaluation(looper.getQueue(), animatorListeners, looper::interrupt, fps, getAllFrames(fps, duration));

		callback = callbackFactory.createCallback(accepter, fps, duration);
		choreographer = new Choreographer(fps, callback);
	}

	@Override
	public void start() {
		if (state == STATE_READY) {
			looper.start();
			choreographer.start();
			state = STATE_RUNNING;
		} else if (state == STATE_RUNNING) {
			System.out.println("Animator has been started...");
		}
	}

	@Override
	public void stop() {
		looper.interrupt();
		choreographer.stop();
	}

	public boolean isRunning() {
		return callback.isRunning();
	}

	public interface FramesCallbackFactory {
		StateCallback createCallback(FrameAccepter accepter, int fps, int duration);
	}
}
