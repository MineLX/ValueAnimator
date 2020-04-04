package com.zyh.pro.animator.main.animators.valueanimator.loopmodes;

import com.zyh.pro.animator.main.animators.AnimatorBuilder.AnimatorListener;
import com.zyh.pro.animator.main.looper.Message;
import com.zyh.pro.animator.main.looper.MessageQueue;

import java.util.List;

public class Evaluation implements Frames.FrameAccepter {

	private final Runnable onEnd;

	private final MessageQueue queue;

	private final List<AnimatorListener> animatorListeners;

	private final long sleepDelay;

	private long used;

	private int allFrames;

	public Evaluation(MessageQueue queue, List<AnimatorListener> animatorListeners, Runnable onEnd, int fps, int allFrames) {
		this.animatorListeners = animatorListeners;
		this.queue = queue;
		this.onEnd = onEnd;
		this.allFrames = allFrames;

		sleepDelay = (long) (int) (1000f / fps);
	}

	@Override
	public void onStart() {
		animatorListeners.forEach(AnimatorListener::onAnimationStart);
		used = System.currentTimeMillis();
	}

	@Override
	public void onEnd() {
		onEnd.run();
		System.out.println("delayed " + (System.currentTimeMillis() - used - allFrames * sleepDelay));
		animatorListeners.forEach(AnimatorListener::onAnimationEnd);
	}

	@Override
	public void onFrameComes(int frameAt) {
		Message obtain = Message.get();
		obtain.setRateAt(1f * frameAt / allFrames);
		queue.enter(obtain);
	}
}
