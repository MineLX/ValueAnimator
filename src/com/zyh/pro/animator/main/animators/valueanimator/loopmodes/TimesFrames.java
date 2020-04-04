package com.zyh.pro.animator.main.animators.valueanimator.loopmodes;

import com.zyh.pro.animator.main.common.Choreographer;

public abstract class TimesFrames extends Frames {

	private int times;

	TimesFrames(FrameAccepter accepter, int fps, int duration, int times) {
		super(accepter, fps, duration);

		this.times = times;
	}

	@Override
	protected final void onFinalFrame(Choreographer choreographer) {
		if (--times == 0) {
			choreographer.stop();
			return;
		}
		setupForNextTime();
	}

	protected abstract void setupForNextTime();
}
