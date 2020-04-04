package com.zyh.pro.animator.main.common;

import java.util.LinkedList;
import java.util.List;

public class BlockedQueue<E> {

	private final List<E> elements;

	private final Object waitAdditionLock;

	private boolean isAlive;

	public BlockedQueue() {
		elements = new LinkedList<>();
		waitAdditionLock = new Object();
		isAlive = true;
	}

	public E take() {

		if (notEmpty())
			return getHead();

		synchronized (waitAdditionLock) {
			if (!isAlive)
				return null;
			try {
				waitAdditionLock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (notEmpty())
			return getHead();
		return null;
	}

	private E getHead() {
		return elements.remove(0);
	}

	private boolean notEmpty() {
		return elements.size() != 0;
	}

	public void add(E addend) {
		elements.add(addend);
		synchronized (waitAdditionLock) {
			waitAdditionLock.notify();
		}
	}

	public void shutdown() {
		isAlive = false;
		synchronized (waitAdditionLock) {
			waitAdditionLock.notify();
		}
	}
}
