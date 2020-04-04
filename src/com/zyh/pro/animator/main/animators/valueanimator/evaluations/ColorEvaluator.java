package com.zyh.pro.animator.main.animators.valueanimator.evaluations;

import java.awt.*;

public class ColorEvaluator implements Evaluator<Color> {
	
	@Override
	public Color evaluate(float f, Color start, Color end) {
		
		int dstR = (int) convert(start.getRed(), end.getRed(), f);
		int dstG = (int) convert(start.getGreen(), end.getGreen(), f);
		int dstB = (int) convert(start.getBlue(), end.getBlue(), f);
		
		return new Color(dstR, dstG, dstB);
	}
	
	private float convert(int start, int end, float f) {
		return start + (end - start) * f;
	}
}
