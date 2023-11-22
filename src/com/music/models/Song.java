package com.music.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Song {
    private UUID id;
    private String name;
    private String genre;
    private Integer duration;
    private String album;
    private Set<UUID> artists = new HashSet<>();

    public Song() {
        setId(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Set<UUID> getArtists() {
        return artists;
    }

    public void setArtists(Set<UUID> artists) {
        this.artists = artists;
    }
}
