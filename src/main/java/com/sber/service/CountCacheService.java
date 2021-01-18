package com.sber.service;

import com.sber.components.CountCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;

@Service
@DependsOn({"countCache"})
public class CountCacheService {
	private CountCache countCache;

	private final Function<String, Boolean> checkCache = (name) -> countCache.getCache().containsKey(name);

	@Autowired
	public void setCountCache(CountCache countCache) {
		this.countCache = countCache;
	}

	public boolean createCache(String name) {
		if (!checkCache.apply(name)) {
			countCache.setNewCache(name);
			return true;
		} else {
			return false;
		}
	}

	public boolean incrementCache(String name) {
		if (checkCache.apply(name)) {
			countCache.increment(name);
			return true;
		} else {
			return false;
		}
	}

	public int getCache(String name) {
		if (checkCache.apply(name)) {
			return countCache.getCache().get(name);
		} else {
			return -1;
		}
	}

	public boolean removeFromCache(String name) {
		if (checkCache.apply(name)) {
			countCache.removeFromCache(name);
			return true;
		} else {
			return false;
		}
	}

	public int getCacheSum() {
		return countCache.getCacheSum();
	}

	public Set<String> getCacheKeys() {
		return countCache.getCache().keySet();
	}
}
