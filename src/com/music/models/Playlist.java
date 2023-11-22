package com.music.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Playlist {
    private UUID id;
    private String name;
    private List<UUID> songsIds = new ArrayList<UUID>();

    public Playlist() {
        setId(UUID.randomUUID());
    }

    public Playlist(String name) {
        setId(UUID.randomUUID());
        setName(name);
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

    public List<UUID> getSongsIds() {
        return songsIds;
    }

    public void setSongsIds(List<UUID> songsIds) {
        this.songsIds = songsIds;
    }
}
