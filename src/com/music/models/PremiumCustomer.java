package com.music.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PremiumCustomer extends Customer {
    private List<Playlist> Playlists = null;

    public PremiumCustomer() {

    }

    @Override
    public void addPlaylist(String name) {
        Playlist play_temp = new Playlist(name);

        if (Playlists == null) {
            Playlists = new ArrayList<>();
        }

        Playlists.add(play_temp);
    }

    @Override
    public List<Playlist> getPlaylists() {
        return Playlists;
    }

    @Override
    public void addPlaylists(List<Playlist> playlists) {
        if (Playlists == null) {
            Playlists = new ArrayList<>();
        }

        Playlists.addAll(playlists);
    }

    @Override
    public void removePlaylist(UUID playlistId) {
        try {
            for (Playlist play_temp : Playlists) {
                if (play_temp.getId().equals(playlistId) == true) {
                    Playlists.remove(play_temp);
                    return;
                }
            }

            throw new PlaylistNotFoundException("La playlist no existe.");
        } catch (PlaylistNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addSongToPlaylist(UUID playlistId, UUID songId) throws MaxSongsInPlayList {
        try {
            for (Playlist play_temp : Playlists) {
                if (play_temp.getId().equals(playlistId) == true) {
                    play_temp.getSongsIds().add(songId);
                    return;
                }
            }

            throw new PlaylistNotFoundException("La playlist no existe.");
        } catch (PlaylistNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeSongFromPlaylist(UUID playlistId, UUID songId) {
        try {
            for (Playlist play_temp : Playlists) {
                if (play_temp.getId().equals(playlistId) == true) {
                    for (UUID id_temp : play_temp.getSongsIds()) {
                        if (id_temp.equals(songId) == true) {
                            play_temp.getSongsIds().remove(songId);
                            return;
                        }
                    }

                    throw new SongNotFoundException("La cancion no se encontro.");
                }
            }

            throw new PlaylistNotFoundException("La playlist no existe.");
        } catch (PlaylistNotFoundException | SongNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<UUID> getSongsFromPlaylist(UUID playlistId) {
        try {
            for (Playlist play_temp : Playlists) {
                if (play_temp.getId().equals(playlistId) == true) {
                    return play_temp.getSongsIds();
                }
            }

            throw new PlaylistNotFoundException("La playlist no existe.");
        } catch (PlaylistNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public String toCSV(String delimiter) {
        return "Premium" + delimiter + getId().toString() + delimiter + getUser() + delimiter + getPassword()
                + delimiter + getName() +
                delimiter + getLastName() + delimiter + getAge() + delimiter + getFollowedArtistsIds().toString();
    }

    @Override
    public List<String> playlistToCSVLines(String delimiter) {
        List<String> List_temp = new ArrayList<>();

        for (Playlist play_temp : Playlists) {
            List_temp.add(play_temp.getId().toString() + delimiter + play_temp.getName() + delimiter
                    + getId().toString() + delimiter + play_temp.getSongsIds().toString());
        }

        return null;
    }
}
