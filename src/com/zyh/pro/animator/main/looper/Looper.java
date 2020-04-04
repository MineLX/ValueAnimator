package com.zyh.pro.animator.main.looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Looper {

	private static final ExecutorService executors = Executors.newCachedThreadPool(new DaemonFactoryProxy());

	private final MessageQueue queue;

	private final MessageHandler handler;

	public Looper(MessageHandler messageHandler) {
		this.handler = messageHandler;
		queue = new MessageQueue();
	}

	public void start() {
		executors.submit(this::loop);
	}

	private void loop() {
		Message message;
		while ((message = queue.take()) != null) {
			handleMessage(message);
		}
//		System.out.println("Looper.loop() : out");
	}

	private void handleMessage(Message next) {
		handler.handleMessage(next);
		next.unlock();
	}

	public void interrupt() {
		queue.shutdown();
	}

	public MessageQueue getQueue() {
		return queue;
	}

	private static class DaemonFactoryProxy implements ThreadFactory {

		private final ThreadFactory factory;

		private DaemonFactoryProxy() {
			factory = Executors.defaultThreadFactory();
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = factory.newThread(r);
			thread.setDaemon(true);
			return thread;
		}
	}
}
