package com.northcoders.record_shop.model;

import jakarta.persistence.*;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    long id;

    @Column
    String artistName;

    @Column
    int releaseYear;

    @Column
    Genre genre;
}
