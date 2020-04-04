package com.zyh.pro.animator.main.looper;

import java.util.ArrayList;
import java.util.List;

public class Message {

	private static List<Message> store = new ArrayList<>();

	private volatile boolean isLock;

	private float rateAt;

	private Message() {
		isLock = false;
	}

	void unlock() {
		isLock = false;
	}

	public float getRateAt() {
		return rateAt;
	}

	public void setRateAt(float rateAt) {
		this.rateAt = rateAt;
	}

	synchronized public static Message get() {
		for (Message message : store) {
			if (!message.isLock) {
				message.isLock = true;
				return message;
			}
		}
		return createNewCache();
	}

	private static Message createNewCache() {
		Message result = new Message();
		store.add(result);
		result.isLock = true;
		return result;
	}
}
