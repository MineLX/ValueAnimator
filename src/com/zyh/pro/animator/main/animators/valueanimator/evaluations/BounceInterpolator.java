package com.zyh.pro.animator.main.animators.valueanimator.evaluations;

public class BounceInterpolator implements Interpolator {

	private float bounce(float t) {
        return t * t * 8.0f;
    }
	
	@Override
	public float interpolate(float f) {
		f *= 1.1226f;
        if (f < 0.3535f) return bounce(f);
        else if (f < 0.7408f) return bounce(f - 0.54719f) + 0.7f;
        else if (f < 0.9644f) return bounce(f - 0.8526f) + 0.9f;
        else return bounce(f - 1.0435f) + 0.95f;
	}
}
