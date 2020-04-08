package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.valueanimator.evaluations.Evaluator;

public final class Location {
	int x;
	int y;

	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Location(" + x + " , " + y + ")";
	}

	static final class LocationEvaluator implements Evaluator<Location> {
		final Location location = new Location(0, 0);

		@Override
		public Location evaluate(float f, Location start, Location end) {
			location.x = convert(f, start.x, end.x);
			location.y = convert(f, start.y, end.y);
			return location;
		}

		private int convert(float f, int start, int end) {
			return (int) (start + (end - start) * f);
		}
	}
}
