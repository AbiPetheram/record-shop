package com.northcoders.record_shop.service;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.Genre;

import java.util.List;
import java.util.Optional;

public interface RecordShopService {
    List<Album> getAllAlbums(String artist, Genre genre, Integer year);
    Optional<Album> getAlbumById(Long id);
    Album insertAlbum(Album album);
    Album updateAlbumById(Album album, Long id);
    boolean deleteAlbumById(Long id);
    List<Album> getAllAlbumsByArtist(String artist);
}
