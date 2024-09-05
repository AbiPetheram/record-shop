package com.northcoders.record_shop.model;

import jakarta.persistence.*;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    long id;

    @Column
    String albumName;

    @Column
    String artistName;

    @Column
    int releaseYear;

    @Column
    Genre genre;

    @Column
    int stock;

    public Album() {
    }

    public Album(long id, String albumName, String artistName, int releaseYear, Genre genre, int stock) {
        this.id = id;
        this.albumName = albumName;
        this.artistName = artistName;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.stock = stock;
    }
}
