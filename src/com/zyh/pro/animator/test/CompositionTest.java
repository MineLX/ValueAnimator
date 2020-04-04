package com.zyh.pro.animator.test;

import com.zyh.pro.animator.main.common.Composition;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CompositionTest {
	@Test
	public void iterate() {
		Composition<String> strings = new Composition<>();
		strings.with("1").with("2")
				.after("3")
				.after("4");
		int sum = 0;
		for (String string : strings) {
			sum ++;
			assertThat(string, is(String.valueOf(sum)));
		}
		assertThat(sum, is(4));
	}


	@Test
	public void size_N_level() {
		Composition<String> strings = new Composition<String>().with("1").with("2").after("3");
		assertThat(strings.size(), is(3));
		assertThat(strings.level(), is(2));
	}

	@Test
	public void after() {
		Composition<String> strings = new Composition<>();
		List<List<String>> actual = strings.after("after").toList();
		assertThat(actual.toString(), is("[[], [after]]"));
	}

	@Test
	public void simple() {
		Composition<String> composition = new Composition<>();
		List<List<String>> list = composition.with("one").with("two").after("three").with("four").toList();
		assertThat(list.toString(), is("[[one, two], [three, four]]"));
	}
}
