package com.northcoders.record_shop.controller;

import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.Genre;
import com.northcoders.record_shop.service.RecordShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/album")
public class RecordShopController {

    @Autowired
    RecordShopService recordShopService;

    @GetMapping
    @Cacheable("getAllAlbumCache")
    public ResponseEntity<List<Album>> getAllAlbums(@RequestParam(required = false) String artist,
                                                    @RequestParam(required = false) Genre genre,
                                                    @RequestParam(required = false) Integer year){
        List<Album> albums = new ArrayList<>();
        albums = recordShopService.getAllAlbums(artist, genre, year);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Cacheable("getAlbumCache")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id){
        Optional<Album> album = recordShopService.getAlbumById(id);
        if(album.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(album.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    @CacheEvict(cacheNames = {"getAllAlbumCache", "getAlbumCache"}, allEntries = true)
    public ResponseEntity<Album> insertAlbum(@RequestBody Album album){
        Album returnedAlbum = recordShopService.insertAlbum(album);
        return new ResponseEntity<>(returnedAlbum, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CacheEvict(cacheNames = {"getAllAlbumCache", "getAlbumCache"}, allEntries = true)
    public ResponseEntity<Album> updateAlbumById(@PathVariable Long id, @RequestBody Album album){
        try{
            Album returnedAlbum = recordShopService.updateAlbumById(album, id);
            return new ResponseEntity<>(returnedAlbum, HttpStatus.OK);
        } catch (RuntimeException e){
           System.err.println(e.getMessage());
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = {"getAllAlbumCache", "getAlbumCache"}, allEntries = true)
    public ResponseEntity<String> deleteAlbumById(@PathVariable Long id){
        if(!recordShopService.deleteAlbumById(id)){
            return new ResponseEntity<>("Album not found! Nothing deleted.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Album has been successfully deleted!", HttpStatus.OK);
        }
    }
}
