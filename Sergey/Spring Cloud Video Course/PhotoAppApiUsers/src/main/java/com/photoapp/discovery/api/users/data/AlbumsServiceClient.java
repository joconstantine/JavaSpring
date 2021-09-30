package com.photoapp.discovery.api.users.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.photoapp.discovery.api.users.ui.model.AlbumResponseModel;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@FeignClient(name = "albums-ws")
public interface AlbumsServiceClient {
	
	@GetMapping("/users/{id}/albums")
	@Bulkhead(name="albums-ws-getAlbums", fallbackMethod = "getAlbumsFallback")
	public List<AlbumResponseModel> getAlbums(@PathVariable String id);
	
	public static List<AlbumResponseModel> getAlbumsFallback(String id, Exception e) {
		return new ArrayList<>();
	}
}

