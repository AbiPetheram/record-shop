package com.northcoders.record_shop.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.record_shop.model.Album;
import com.northcoders.record_shop.model.Genre;
import com.northcoders.record_shop.service.RecordShopServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class RecordShopControllerTest {
    @Mock
    private RecordShopServiceImpl mockRecordShopService;

    @InjectMocks
    private RecordShopController recordShopController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(recordShopController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllAlbumsReturnsAlbums() throws Exception{
        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Avenged Sevenfold", "Avenged Sevenfold", 2007, Genre.METAL, 10));
        albums.add(new Album(2L, "The Colour And The Shape", "Foo Fighters", 1997, Genre.ROCK, 10));
        albums.add(new Album(3L, "Mansion", "NF", 2015, Genre.RAP, 10));


        when(mockRecordShopService.getAllAlbums()).thenReturn(albums);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/album/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].albumName").value("Avenged Sevenfold"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].albumName").value("The Colour And The Shape"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].albumName").value("Mansion"));
    }

    @Test
    public void testGetAlbumByIdReturnsAlbum() throws Exception{
        Optional<Album> album = Optional.of(new Album(1L, "Avenged Sevenfold",
                "Avenged Sevenfold", 2007, Genre.METAL, 10));

        when(mockRecordShopService.getAlbumById(1L)).thenReturn(album);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/album/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.albumName").value("Avenged Sevenfold"));
    }

    @Test
    public void testInsertAlbum() throws Exception{
        Album album = new Album(1L, "Avenged Sevenfold",
                "Avenged Sevenfold", 2007, Genre.METAL, 10);
        when(mockRecordShopService.insertAlbum(album)).thenReturn(album);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.post("/api/v1/album")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.albumName").value("Avenged Sevenfold"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("METAL"));
    }

    @Test
    public void testUpdateAlbumById() throws Exception{
        Album album = new Album(1L, "Avenged Sevenfold",
                "Avenged Sevenfold", 2007, Genre.METAL, 10);
        when(mockRecordShopService.updateAlbumById(album, 1L)).thenReturn(album);
        this.mockMvcController.perform(
                MockMvcRequestBuilders.put("/api/v1/album/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(album)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.albumName").value("Avenged Sevenfold"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("METAL"));
    }

    @Test
    public void testDeleteAlbumByIdValidId() throws Exception{
        when(mockRecordShopService.deleteAlbumById(1L)).thenReturn(true);
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/api/v1/album/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Album has been successfully deleted!"));
    }

    @Test
    public void testDeleteAlbumByIdInvalidId() throws Exception{
        when(mockRecordShopService.deleteAlbumById(1L)).thenReturn(false);
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/api/v1/album/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Album not found! Nothing deleted."));
    }

    @Test
    public void testGetAllAlbumsByArtistReturnsAlbums() throws Exception{
        List<Album> albums = new ArrayList<>();
        albums.add(new Album(1L, "Avenged Sevenfold", "Avenged Sevenfold", 2007, Genre.METAL, 10));
        albums.add(new Album(2L, "Life Is But a Dream...", "Avenged Sevenfold", 2023, Genre.METAL, 10));
        albums.add(new Album(3L, "Nightmare", "Avenged Sevenfold", 2010, Genre.METAL, 10));

        when(mockRecordShopService.getAllAlbumsByArtist("Avenged+Sevenfold")).thenReturn(albums);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/album?artist=Avenged+Sevenfold"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].albumName").value("Avenged Sevenfold"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].albumName").value("Life Is But a Dream..."))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].albumName").value("Nightmare"));
    }
}