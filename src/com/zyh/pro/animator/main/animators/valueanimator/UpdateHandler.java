package com.zyh.pro.animator.main.animators.valueanimator;

import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Interpolator;
import com.zyh.pro.animator.main.looper.Message;
import com.zyh.pro.animator.main.looper.MessageHandler;

public abstract class UpdateHandler implements MessageHandler {

	private final Interpolator interpolator;

	UpdateHandler(Interpolator interpolator) {
		this.interpolator = interpolator;
	}

	@Override
	public final void handleMessage(Message message) {
		onUpdate(interpolator.interpolate(message.getRateAt()));
	}

	protected abstract void onUpdate(float rateAt);

}
