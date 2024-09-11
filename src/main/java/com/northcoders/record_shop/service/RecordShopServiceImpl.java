package com.northcoders.record_shop.service;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.repository.RecordShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecordShopServiceImpl implements RecordShopService{

    @Autowired
    RecordShopRepository recordShopRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        recordShopRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Optional<Album> getAlbumById(Long id) {
        return recordShopRepository.findById(id);
    }

    @Override
    public Album insertAlbum(Album album) {
        return recordShopRepository.save(album);
    }

    @Override
    public Album updateAlbumById(Album album, Long id) {
        album.setId(id);
        if(recordShopRepository.existsById(id)){
            return recordShopRepository.save(album);
        } else {
            throw new RuntimeException("No book exists with that ID");
        }
    }

    @Override
    public boolean deleteAlbumById(Long id) {
        if(!recordShopRepository.existsById(id)){
            return false;
        } else {
            recordShopRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public List<Album> getAllAlbumsByArtist(String artist) {
        List<Album> albums = new ArrayList<>();
        recordShopRepository.findAllAlbumsByArtistName(artist).forEach(albums::add);
        return albums;
    }
}
