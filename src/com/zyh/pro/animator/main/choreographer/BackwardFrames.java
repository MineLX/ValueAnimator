package com.zyh.pro.animator.main.choreographer;

public class BackwardFrames extends Frames {

	public BackwardFrames(FrameAccepter accepter, int fps, int duration) {
		super(accepter, fps, duration);
	}

	@Override
	protected int initFrameAt() {
		return allFrames;
	}

	@Override
	protected int onForward(int frameAt) {
		return frameAt - 1;
	}

	@Override
	protected int finalFrameAt() {
		return 0;
	}
}
