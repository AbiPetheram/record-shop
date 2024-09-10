package com.northcoders.record_shop.repository;

import com.northcoders.record_shop.model.Album;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordShopRepository extends CrudRepository<Album, Long>, JpaSpecificationExecutor<Album> {
    List<Album> findAllAlbumsByArtistName(String artist);
//    List<Album> findAllAlbumsByGenre(String genre);
//    List<Album> findAllAlbumsByReleaseYear(String year);
}
