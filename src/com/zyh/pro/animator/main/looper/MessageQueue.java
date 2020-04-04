package com.zyh.pro.animator.main.looper;

import com.zyh.pro.animator.main.common.BlockedQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageQueue {

	private final BlockedQueue<Message> messages;

	public MessageQueue() {
		messages = new BlockedQueue<>();
	}

	Message take() {
		return messages.take();
	}

	public void enter(Message item) {
		messages.add(item);
	}

	public void shutdown() {
		messages.shutdown();
	}
}
