package com.zyh.pro.animator.main.choreographer;

public abstract class StateCallback implements Choreographer.Callback {

	private boolean isRunning;

	@Override
	public final void onStart() {
		isRunning = true;
		onStartNotify();
	}

	@Override
	public final void onEnd() {
		isRunning = false;
		onEndNotify();
	}

	protected abstract void onStartNotify();

	protected abstract void onEndNotify();

	public boolean isRunning() {
		return isRunning;
	}
}
