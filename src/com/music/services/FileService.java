package com.music.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.music.models.Artist;
import com.music.models.Customer;
import com.music.models.Playlist;
import com.music.models.PremiumCustomer;
import com.music.models.Song;

public class FileService {
    private Map<UUID, Artist> mapArtists = new HashMap<>();
    private List<Song> list_Song = new ArrayList<Song>();
    private List<Customer> list_Customers = new ArrayList<Customer>();

    public FileService() {

    }

    public Map<UUID, Artist> getMapArtists() {
        return mapArtists;
    }

    public void setMapArtists(Map<UUID, Artist> mapArtists) {
        this.mapArtists = mapArtists;
    }

    public List<Song> getList_Song() {
        return list_Song;
    }

    public void setList_Song(List<Song> list_Song) {
        this.list_Song = list_Song;
    }

    public List<Customer> getList_Customers() {
        return list_Customers;
    }

    public void setList_Customers(List<Customer> list_Customers) {
        this.list_Customers = list_Customers;
    }

    public void SaveAll() {
        FileWriter File_Save = null;

        try {
            File_Save = new FileWriter("ARTISTS.csv");
            BufferedWriter bw = new BufferedWriter(File_Save);

            for (Map.Entry<UUID, Artist> data : mapArtists.entrySet()) {
                bw.write(data.getKey().toString() + ";" + data.getValue().getName());
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {

        } finally {
            try {
                File_Save.close();
            } catch (IOException e) {
                System.out.println("\nError en el archivo de artistas!");
            }
        }

        try {
            File_Save = new FileWriter("SONGS.csv");
            BufferedWriter bw = new BufferedWriter(File_Save);

            for (Song song_temp : list_Song) {
                bw.write(song_temp.getId().toString() + ";" + song_temp.getName() +
                        ";{"
                        + song_temp.getArtists().toString().substring(1, song_temp.getArtists().toString().length() - 1)
                        + "};" +
                        song_temp.getGenre() + ";" + song_temp.getDuration() + ";" + song_temp.getAlbum());
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            try {
                File_Save.close();
            } catch (IOException x) {
                System.out.println("\nError en el archivo de canciones!");
            }
        }

        try {
            File_Save = new FileWriter("CUSTOMERS.csv");
            BufferedWriter bw = new BufferedWriter(File_Save);

            for (Customer customer_temp : list_Customers) {
                if (customer_temp instanceof PremiumCustomer) {
                    bw.write("Premium;");
                } else {
                    bw.write("Regular;");
                }

                bw.write(customer_temp.getId().toString() + ";" + customer_temp.getUser() + ";"
                        + customer_temp.getPassword() + ";" + customer_temp.getName() + ";" +
                        customer_temp.getLastName() + ";" + customer_temp.getAge() + ";{" +
                        customer_temp.getFollowedArtistsIds().toString().substring(1,
                                customer_temp.getFollowedArtistsIds().toString().length() - 1)
                        + "}");

                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            try {
                File_Save.close();
            } catch (IOException x) {
                System.out.println("\nError en el archivo de usuarios!");
            }
        }

        try {
            File_Save = new FileWriter("PLAYLISTS.csv");
            BufferedWriter bw = new BufferedWriter(File_Save);

            for (Customer customer_temp : list_Customers) {
                for (Playlist play_temp : customer_temp.getPlaylists()) {
                    bw.write(play_temp.getId().toString() + ";" + play_temp.getName() + ";"
                            + customer_temp.getId().toString() + ";{" +
                            play_temp.getSongsIds().toString().substring(1,
                                    play_temp.getSongsIds().toString().length() - 1)
                            + "}");

                    bw.newLine();
                }
            }

            bw.close();
        } catch (Exception e) {
            try {
                File_Save.close();
            } catch (IOException x) {
                System.out.println("\nError en el archivo de canciones!");
            }
        }

    }

    public Map<UUID, Integer> Followers() {
        Map<UUID, Integer> followers_temp = new HashMap<>();

        for (Customer customer_temp : list_Customers) {
            for (UUID artists_id : customer_temp.getFollowedArtistsIds()) {
                followers_temp.merge(artists_id, 1, Integer::sum);
            }
        }

        return followers_temp;
    }

    public Map<UUID, Float> Popularity() {
        float largest = 0;
        Map<UUID, Float> popular_temp = new HashMap<>();

        for (Map.Entry<UUID, Artist> data : mapArtists.entrySet()) {
            popular_temp.put(data.getKey(), 0.0f);

            for (Customer customer_temp : list_Customers) {
                for (Playlist play_temp : customer_temp.getPlaylists()) {
                    for (UUID id_song_temp : play_temp.getSongsIds()) {
                        for (Song song_temp : list_Song) {
                            if (song_temp.getId().equals(id_song_temp)) {
                                for (UUID id_artist : song_temp.getArtists()) {
                                    if (id_artist.equals(data.getKey())) {
                                        popular_temp.merge(data.getKey(), 1.0f, Float::sum);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (largest < popular_temp.get(data.getKey())) {
                largest = popular_temp.get(data.getKey());
            }
        }

        for (Map.Entry<UUID, Float> entry : popular_temp.entrySet()) {
            UUID id_artist = entry.getKey();
            Float value = entry.getValue();

            float new_value = value * (100 / largest);

            popular_temp.put(id_artist, new_value);
        }

        return popular_temp;
    }

    public List<UUID> Songs_all_PlayList() {
        boolean bool_song = false, bool_song_found = false, bool_not_valide = false;
        List<UUID> song_list_temp = new ArrayList<>();

        for (Song song_temp : list_Song) {
            for (Customer customer_temp : list_Customers) {
                for (Playlist play_temp : customer_temp.getPlaylists()) {
                    for (UUID song_id : play_temp.getSongsIds()) {
                        if (song_id.equals(song_temp.getId())) {
                            bool_song = true;
                            if (!bool_song_found) {
                                bool_song_found = true;
                            }

                            break;
                        }
                    }

                    if (!(bool_song_found == true && bool_song == true)) {
                        bool_not_valide = true;
                        break;
                    }

                    bool_song = false;
                }

                if (bool_not_valide) {
                    break;
                }
            }

            if (!bool_not_valide) {
                song_list_temp.add(song_temp.getId());
            }

            bool_not_valide = false;
            bool_song_found = false;
        }

        return song_list_temp;
    }
}
