package com.northcoders.record_shop.service;

import com.northcoders.record_shop.repository.AlbumSpecifications;
import com.northcoders.record_shop.repository.RecordShopRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.Genre;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RecordShopServiceImplTest {

    @Mock
    private RecordShopRepository mockRecordShopRepository;

    @InjectMocks
    private RecordShopServiceImpl recordShopService;

    @Test
    public void testGetAllAlbums(){
        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Avenged Sevenfold", "Avenged Sevenfold", 2007, Genre.METAL, 10));
        albums.add(new Album(2L, "Nightmare", "Avenged Sevenfold", 2010, Genre.METAL, 10));
        albums.add(new Album(3L, "The Colour And The Shape", "Foo Fighters", 1997, Genre.ROCK, 10));
        albums.add(new Album(4L, "Mansion", "NF", 2015, Genre.RAP, 10));
        albums.add(new Album(5L, "At Folsom Prison", "Johnny Cash", 1968, Genre.COUNTRY, 10));

        when(mockRecordShopRepository.findAll(Mockito.any(Specification.class))).thenReturn(albums);

        List<Album> actualResult = recordShopService.getAllAlbums(null, null, null);

        assertThat(actualResult).hasSize(5);
        assertThat(actualResult).isEqualTo(albums);
    }

    @Test
    public void testGetAllAlbumsByArtist(){
        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Avenged Sevenfold", "Avenged Sevenfold", 2007, Genre.METAL, 10));
        albums.add(new Album(2L, "Nightmare", "Avenged Sevenfold", 2010, Genre.METAL, 10));


        when(mockRecordShopRepository.findAll(Mockito.any(Specification.class))).thenReturn(albums);

        List<Album> actualResult = recordShopService.getAllAlbums("Avenged Sevenfold", null, null);

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(albums);
    }

    @Test
    public void testGetAllAlbumsByGenre(){
        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Avenged Sevenfold", "Avenged Sevenfold", 2007, Genre.METAL, 10));
        albums.add(new Album(2L, "Nightmare", "Avenged Sevenfold", 2010, Genre.METAL, 10));


        when(mockRecordShopRepository.findAll(Mockito.any(Specification.class))).thenReturn(albums);

        List<Album> actualResult = recordShopService.getAllAlbums(null, Genre.METAL, null);

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(albums);
    }

    @Test
    public void testGetAllAlbumsByYear(){
        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Avenged Sevenfold", "Avenged Sevenfold", 2007, Genre.METAL, 10));


        when(mockRecordShopRepository.findAll(Mockito.any(Specification.class))).thenReturn(albums);

        List<Album> actualResult = recordShopService.getAllAlbums(null, null, 2007);

        assertThat(actualResult).hasSize(1);
        assertThat(actualResult).isEqualTo(albums);
    }

    @Test
    public void testGetAlbumByIdReturnsAlbum(){
        Optional<Album> album = Optional.of(new Album(1L, "Avenged Sevenfold",
                "Avenged Sevenfold", 2007, Genre.METAL, 10));

        when(mockRecordShopRepository.findById(1L)).thenReturn(album);

        Optional<Album> actualResult = recordShopService.getAlbumById(1L);

        assertThat(actualResult).isEqualTo(album);
    }

    @Test
    public void testAddAlbumReturnsAlbum(){
        Album album = new Album(1L, "Avenged Sevenfold",
                "Avenged Sevenfold", 2007, Genre.METAL, 10);
        when(mockRecordShopRepository.save(album)).thenReturn(album);
        Album actualResult = recordShopService.insertAlbum(album);
        assertThat(actualResult).isEqualTo(album);
    }

    @Test
    public void testUpdateAlbum(){
        Album album = new Album(1L, "Avenged Sevenfold",
                "Avenged Sevenfold", 2007, Genre.METAL, 10);
        when(mockRecordShopRepository.existsById(1L)).thenReturn(true);
        when(mockRecordShopRepository.save(album)).thenReturn(album);
        Album actualResult = recordShopService.updateAlbumById(album, 1L);
        assertThat(actualResult).isEqualTo(album);
    }

    @Test
    public void testUpdateAlbumWithInvalidId(){
        Album album = new Album(1L, "Avenged Sevenfold",
                "Avenged Sevenfold", 2007, Genre.METAL, 10);
        when(mockRecordShopRepository.save(album)).thenReturn(album);
        assertThrows(RuntimeException.class,
                ()-> recordShopService.updateAlbumById(album, 1L)
        );
    }

    @Test
    public void testDeleteAlbumById(){
        when(mockRecordShopRepository.existsById(1L)).thenReturn(true);
        boolean result = recordShopService.deleteAlbumById(1L);
        assertThat(result).isTrue();
    }

    @Test
    public void testDeleteAlbumByIdInvalidId(){
        when(mockRecordShopRepository.existsById(1L)).thenReturn(false);
        boolean result = recordShopService.deleteAlbumById(1L);
        assertThat(result).isFalse();
    }
}