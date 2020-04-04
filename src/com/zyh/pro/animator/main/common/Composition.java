package com.zyh.pro.animator.main.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Composition<E> implements Iterable<E> {

	private final List<List<E>> elements;

	private int index;

	public Composition() {
		elements = new ArrayList<>();
		elements.add(new ArrayList<>());
	}

	public Composition<E> with(E with) {
		elements.get(index).add(with);
		return this;
	}

	public Composition<E> after(E after) {
		List<E> addend = new ArrayList<>();
		addend.add(after);
		elements.add(addend);
		index++;
		return this;
	}

	public List<List<E>> toList() {
		return elements;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iter();
	}

	public int level() {
		return elements.size();
	}

	public int size() {
		return elements.stream()
				.mapToInt(List::size)
				.reduce(0, Integer::sum);
	}

	private class Iter implements Iterator<E> {
		private int levelIndex = 0;

		private int index = 0;

		@Override
		public boolean hasNext() {
			return levelIndex != level();
		}

		@Override
		public E next() {
			E result = currentLevel().get(index);
			nextElement();
			if (reachEndElement())
				nextLevel();
			return result;
		}

		private void nextElement() {
			index++;
		}

		private boolean reachEndElement() {
			return index == currentLevel().size();
		}

		private void nextLevel() {
			levelIndex ++;
			index = 0;
		}

		private List<E> currentLevel() {
			return elements.get(levelIndex);
		}
	}
}
