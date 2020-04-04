package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.animators.CompositeAnimatorBuilder;
import org.junit.Test;

import static com.zyh.pro.animator.test.CompositeTest.getCompositeAnimator;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CompositeAnimatorBuilderTest {
	@Test
	public void setDuration() {
		CompositeAnimatorBuilder builder = getCompositeAnimator();
		assertThat(builder.getDuration(), is(3000));
		builder.setDuration(5000);
		assertThat(builder.getDuration(), is(4999));
	}

	@Test
	public void getDuration() {
		CompositeAnimatorBuilder compositeAnimator = getCompositeAnimator();
		int duration = compositeAnimator.getDuration();
		assertThat(duration, is(3000));
	}
}
