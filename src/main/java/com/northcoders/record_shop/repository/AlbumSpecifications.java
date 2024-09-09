package com.northcoders.record_shop.repository;

import com.northcoders.record_shop.model.Album;
import org.springframework.data.jpa.domain.Specification;

public class AlbumSpecifications {
    public static Specification<Album> hasArtist(String artist){
        return ((root, query, criteriaBuilder) ->
                artist == null ? null : criteriaBuilder.like(root.get("artistName"), "%" + artist + "%"));
    }

    public static Specification<Album> hasGenre(String genre){
        return ((root, query, criteriaBuilder) ->
                genre == null ? null : criteriaBuilder.like(root.get("genre"), "%" + genre + "%"));
    }

    public static Specification<Album> hasYear(String year){
        return ((root, query, criteriaBuilder) ->
                year == null ? null : criteriaBuilder.like(root.get("releaseYear"), "%" + year + "%"));
    }
}
