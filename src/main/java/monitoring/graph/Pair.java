package monitoring.graph;

import org.apache.commons.lang3.ObjectUtils;

/**
 * Utility class for holding a pair of data
 * 
 * @param <T>
 *            type of first data
 * @param <S>
 *            type of second data
 */
public class Pair<T, S> {
	private T first;
	private S second;

	public Pair(T first, S second) {
		this.first = first;
		this.second = second;
	}

	public static <T, S> Pair<T, S> of(T first, S second) {
		return new Pair<T, S>(first, second);
	}

	public T getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}

	public int hashCode() {
		return (first == null ? 1 : first.hashCode()) * 17 + (second == null ? 1 : second.hashCode()) * 19;
	}

	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}

		if (!(other instanceof Pair)) {
			return false;
		}

		Pair<T, S> otherPair = (Pair<T, S>) other;
		return (ObjectUtils.equals(first, otherPair.first) && ObjectUtils.equals(second, otherPair.second));
	}
}
