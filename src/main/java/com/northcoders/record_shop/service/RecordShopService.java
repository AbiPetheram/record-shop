package com.northcoders.record_shop.service;

import com.northcoders.record_shop.model.Album;

import java.util.List;
import java.util.Optional;

public interface RecordShopService {
    List<Album> getAllAlbums();
    Optional<Album> getAlbumById(Long id);
}
