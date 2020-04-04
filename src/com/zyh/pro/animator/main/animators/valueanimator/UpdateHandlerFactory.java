package com.zyh.pro.animator.main.animators.valueanimator;

import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Interpolator;

public abstract class UpdateHandlerFactory {

	protected final Interpolator interpolator;

	UpdateHandlerFactory(Interpolator interpolator) {
		this.interpolator = interpolator;
	}

	public abstract UpdateHandler createUpdateHandler();
}
