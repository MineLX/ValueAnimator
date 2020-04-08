package com.zyh.pro.animator.main.choreographer;

public class TimesForwardFrames extends TimesFrames {

	public TimesForwardFrames(FrameAccepter accepter, int fps, int duration, int times) {
		super(accepter, fps, duration, times);
	}

	@Override
	protected void setupForNextTime() {
		// reset frameAt
		frameAt = 0;
	}
}
