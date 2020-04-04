package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.Animators;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FrameTest {
	public static void main(String[] args) throws InterruptedException {
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame.setSize(1000, 600);
//		frame.setLayout(null);
//		Canvas canvas = new Canvas();
//		canvas.setBackground(Color.decode("#bb2266"));
//		canvas.setBounds(10, 10, 50, 50);
//		frame.add(canvas);
//		frame.setVisible(true);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outputStream);
		for (int i = 0; i < 60; i++) {
			Animators.valueFloat(10, 500, 4000, out::println).start();
			Thread.sleep(10);
		}
	}
}
