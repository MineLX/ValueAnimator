package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.common.BlockedQueue;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BlockedQueueTest {
	@Test
	public void interrupt_test() throws InterruptedException {
		BlockedQueue<String> queue = new BlockedQueue<>();
		Executors.newSingleThreadExecutor().submit(() -> {
			for (int i = 0; i < 10; i++) {
				String take = queue.take();
				System.out.println("take = " + take);
				assertNull(take);
			}
		});
		Thread.sleep(1000);
		System.out.println("shut down it");
		queue.shutdown();
		Thread.sleep(2000);
	}

	@Test
	public void multiple_test() throws InterruptedException {
		BlockedQueue<String> queue = new BlockedQueue<>();
		Executors.newSingleThreadExecutor().submit(() -> {
			long used = System.currentTimeMillis();
			for (int i = 0; i < 500; i++) {
				queue.add(i + "");
			}
			System.out.println("add TIME = " + (System.currentTimeMillis() - used));
		});

		Executors.newSingleThreadExecutor().submit(() -> {
			long used = System.currentTimeMillis();
			for (int i = 0; i < 500; i++) {
				String take = queue.take();
				assertThat(take, is(i + ""));
			}
			System.out.println("take TIME = " + (System.currentTimeMillis() - used));
		});
		Thread.sleep(50000);
	}

	@Test
	public void add() throws InterruptedException {
		BlockedQueue<String> queue = new BlockedQueue<>();
		Executors.newSingleThreadExecutor().submit(() -> {
			System.out.println("start taking...");
			assertThat(queue.take(), is("hello"));
			System.out.println("end assertion");
		});
		Thread.sleep(1000);
		queue.add("hello");
		Thread.sleep(1000);
	}
}
