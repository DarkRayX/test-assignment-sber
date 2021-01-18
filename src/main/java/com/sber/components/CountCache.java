package com.sber.components;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountCache {
	private final int INITIAL_COUNT = 0;

	@Getter
	private final Map<String, Integer> cache = new HashMap<>();

	public void setNewCache(String name) {
		cache.put(name, INITIAL_COUNT);
	}

	@SuppressWarnings("ConstantConditions")
	public void increment(String name) {
		cache.compute(name, (key, value) -> value++);
	}

	public void removeFromCache(String name) {
		cache.remove(name);
	}

	public int getCacheSum() {
		return cache.values().stream().reduce(0, Integer::sum);
	}
}
