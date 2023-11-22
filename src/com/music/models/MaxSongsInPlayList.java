package com.music.models;

public class MaxSongsInPlayList extends RuntimeException {
    public MaxSongsInPlayList(String message) {
        super(message);
    }
}

class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(String message) {
        super(message);
    }
}

class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String message) {
        super(message);
    }
}