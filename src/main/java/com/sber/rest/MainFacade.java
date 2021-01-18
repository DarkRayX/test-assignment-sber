package com.sber.rest;

import com.sber.service.CountCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.function.Function;

@RestController
@RequestMapping(path = "/cache")
public class MainFacade {
	private CountCacheService service;

	private final Function<Boolean, ResponseEntity> conditionResponse = res -> res ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();

	@Autowired
	public void setService(CountCacheService service) {
		this.service = service;
	}

	@GetMapping(path = "/set-new-cache")
	public ResponseEntity setNewCache(@RequestParam String cacheName) {
		return conditionResponse.apply(service.createCache(cacheName));
	}

	@GetMapping(path = "/increment-cache")
	public ResponseEntity incrementCache(@RequestParam String cacheName) {
		return conditionResponse.apply(service.incrementCache(cacheName));
	}

	@GetMapping(path = "/get-cache")
	public ResponseEntity<Integer> getCache(@RequestParam String cacheName) {
		int res = service.getCache(cacheName);
		if (res < 0) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(res);
		}
	}

	@GetMapping(path = "/remove-cache")
	public ResponseEntity removeCache(@RequestParam String cacheName) {
		return conditionResponse.apply(service.removeFromCache(cacheName));
	}

	@GetMapping(path = "/get-caches-sum")
	public ResponseEntity getCachesSum() {
		return ResponseEntity.ok(service.getCacheSum());
	}

	@GetMapping(path = "/get-all")
	public Set<String> getAll() {
		return service.getCacheKeys();
	}
}
