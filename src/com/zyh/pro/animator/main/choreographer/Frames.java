package com.zyh.pro.animator.main.choreographer;

public class Frames extends StateCallback {

	private final FrameAccepter accepter;

	protected final int allFrames;

	protected int frameAt;

	public Frames(FrameAccepter accepter, int fps, int duration) {
		this.accepter = accepter;

		allFrames = getAllFrames(fps, duration);
		frameAt = initFrameAt();
	}

	@Override
	public void onNotify(Choreographer choreographer) {
		if (frameAt == finalFrameAt()) {
			accepter.onFrameComes(frameAt);
			onFinalFrame(choreographer);
			return;
		}
		accepter.onFrameComes(frameAt);
		frameAt = onForward(frameAt);
	}

	@Override
	public void onStartNotify() {
		accepter.onStart();
	}

	@Override
	public void onEndNotify() {
		accepter.onEnd();
	}

	protected int initFrameAt() {
		return 0;
	}

	protected int onForward(int frameAt) {
		return frameAt + 1;
	}

	protected int finalFrameAt() {
		return allFrames;
	}

	protected void onFinalFrame(Choreographer choreographer) {
		choreographer.stop();
	}

	public static int getAllFrames(int fps, int duration) {
		return (int) ((duration / 1000f) * fps);
	}

	public interface FrameAccepter {
		void onFrameComes(int frameAt);

		void onStart();

		void onEnd();
	}
}
