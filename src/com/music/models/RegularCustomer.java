package com.music.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RegularCustomer extends Customer {
    private static final String DEFAULT_PLAYLIST_NAME = "Playlist";
    private static final int BOUND = 10;
    private Playlist Playlists = null;

    public RegularCustomer() {
        int random = new Random().nextInt(BOUND);
        Playlists = new Playlist((DEFAULT_PLAYLIST_NAME + random));
    }

    @Override
    public void addPlaylist(String name) {
        throw new UnsupportedOperationException("No puede agregar una playlist. Usted es usuario regular.");
    }

    @Override
    public List<Playlist> getPlaylists() {
        if (Playlists == null) {
            return null;
        } else {
            List<Playlist> play_temp = new ArrayList<>();
            play_temp.add(Playlists);
            return play_temp;
        }

    }

    @Override
    public void addPlaylists(List<Playlist> playlists) {
        try {
            if (playlists.size() > 1 || playlists.size() == 0) {
                throw new UnsupportedOperationException("No se le permite tener mas de una playlist.");
            } else {
                Playlists = playlists.get(0);
                return;
            }
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removePlaylist(UUID playlistId) {
        throw new UnsupportedOperationException("No puede eliminar su playlist. Usted es usuario regular.");
    }

    @Override
    public void addSongToPlaylist(UUID playlistId, UUID songId) throws MaxSongsInPlayList {
        try {
            if (Playlists.getSongsIds().size() == 10) {
                throw new MaxSongsInPlayList("Ha llegado al maximo de canciones permitido.");
            } else {
                if (Playlists.getId().equals(playlistId) == true) {
                    Playlists.getSongsIds().add(songId);
                }
            }
        } catch (MaxSongsInPlayList e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeSongFromPlaylist(UUID playlistId, UUID songId) {
        try {
            if (Playlists.getId().equals(playlistId) == true) {
                for (UUID id_temp : Playlists.getSongsIds()) {
                    if (id_temp.equals(songId) == true) {
                        Playlists.getSongsIds().remove(songId);
                        return;
                    }
                }

                throw new SongNotFoundException("La cancion no se encontro.");
            }

            throw new PlaylistNotFoundException("La playlist no existe.");
        } catch (PlaylistNotFoundException | SongNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<UUID> getSongsFromPlaylist(UUID playlistId) {
        try {
            if (Playlists.getId().equals(playlistId) == true) {
                return Playlists.getSongsIds();
            }

            throw new PlaylistNotFoundException("La playlist no existe.");
        } catch (PlaylistNotFoundException | SongNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public String toCSV(String delimiter) {
        return "Regular" + delimiter + getId().toString() + delimiter + getUser() + delimiter + getPassword()
                + delimiter + getName() +
                delimiter + getLastName() + delimiter + getAge() + delimiter + getFollowedArtistsIds().toString();
    }

    @Override
    public List<String> playlistToCSVLines(String delimiter) {
        List<String> List_temp = new ArrayList<>();
        List_temp.add(Playlists.getId().toString() + delimiter + Playlists.getName() + delimiter + getId().toString()
                + delimiter + Playlists.getSongsIds().toString());

        return null;
    }
}
