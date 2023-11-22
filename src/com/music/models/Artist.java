package com.music.models;

import java.util.UUID;

public class Artist {
    private UUID id;
    private String name;

    public Artist() {
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
}
