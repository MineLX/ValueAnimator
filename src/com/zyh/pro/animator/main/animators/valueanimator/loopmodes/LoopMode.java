package com.zyh.pro.animator.main.animators.valueanimator.loopmodes;

import com.zyh.pro.animator.main.animators.valueanimator.ValueAnimator;
import com.zyh.pro.animator.main.choreographer.*;

public class LoopMode {
	public static ValueAnimator.FramesCallbackFactory normal() {
		return Frames::new;
	}

	public static ValueAnimator.FramesCallbackFactory reversed() {
		return BackwardFrames::new;
	}

	public static ValueAnimator.FramesCallbackFactory infinity() {
		return infinity(-1);
	}

	public static ValueAnimator.FramesCallbackFactory infinity_reversed() {
		return infinity_reversed(-1);
	}

	public static ValueAnimator.FramesCallbackFactory infinity(int times) {
		return (accepter, fps, duration) -> new TimesForwardFrames(accepter, fps, duration, times);
	}

	public static ValueAnimator.FramesCallbackFactory infinity_reversed(int times) {
		return (accepter, fps, duration) -> new TimesRoundFrames(accepter, fps, duration, times);
	}

	public static ValueAnimator.FramesCallbackFactory reversed_infinity(int times) {
		return (accepter, fps, duration) -> new TimesBackwardFrames(accepter, fps, duration, times);
	}
}
