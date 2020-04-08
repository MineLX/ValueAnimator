package com.zyh.pro.animator.main.choreographer;

public class TimesBackwardFrames extends TimesFrames {

	public TimesBackwardFrames(FrameAccepter accepter, int fps, int duration, int times) {
		super(accepter, fps, duration, times);
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

	@Override
	protected void setupForNextTime() {
		frameAt = allFrames;
	}
}
