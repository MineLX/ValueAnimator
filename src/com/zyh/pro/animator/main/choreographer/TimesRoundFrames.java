package com.zyh.pro.animator.main.choreographer;

public class TimesRoundFrames extends TimesFrames {

	private int factor;

	private int stopAt;

	public TimesRoundFrames(FrameAccepter accepter, int fps, int duration, int times) {
		super(accepter, fps, duration, times);

		factor = 1;
		stopAt = allFrames;
	}

	@Override
	protected int onForward(int frameAt) {
		return frameAt + factor;
	}

	@Override
	protected int finalFrameAt() {
		return stopAt;
	}

	@Override
	protected void setupForNextTime() {
		factor = ~factor + 1;                  // reverse the factor
		frameAt += factor;                     // forward for ONE step to avoid duplicated frameAt
		stopAt = Math.abs(allFrames - stopAt); // reverse destination (for final frame callback)
	}
}
