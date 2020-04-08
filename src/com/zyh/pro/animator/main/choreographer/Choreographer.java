package com.zyh.pro.animator.main.choreographer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Choreographer {

	private static final ExecutorService service = Executors.newCachedThreadPool();

	private final Callback callback;

	private final long initialSleepDelay;

	private long nextSleepDelay;

	private long beginningOfFrame;

	private boolean isAlive;

	public Choreographer(int fps, Callback callback) {
		this.callback = callback;

		initialSleepDelay = (int) (1000f / fps);
		nextSleepDelay = initialSleepDelay;
	}

	public void start() {
		isAlive = true;
		service.submit(this::run);
	}

	public void stop() {
		isAlive = false;
	}

	private void run() {
		callback.onStart();
//		long used = System.currentTimeMillis();
		for (; ; ) {
			beginningOfFrame = System.currentTimeMillis();

			callback.onNotify(this);

			if (!isAlive) break;

			try {
				if (nextSleepDelay >= 0)
					Thread.sleep(nextSleepDelay);
			} catch (InterruptedException ignore) {
				break;
			}
			nextSleepDelay = calculateNextSleepDelay();
		}
//		System.out.println("loop end : " + (System.currentTimeMillis() - used));
		callback.onEnd();
	}

	private long calculateNextSleepDelay() {
		long realSleepMillis = System.currentTimeMillis() - beginningOfFrame;
		long previousInterception = realSleepMillis - nextSleepDelay;
		return initialSleepDelay - previousInterception;
	}

	public interface Callback {
		void onNotify(Choreographer choreographer);

		void onStart();

		void onEnd();
	}
}
