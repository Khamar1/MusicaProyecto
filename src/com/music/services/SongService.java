package com.music.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.music.models.Song;

public class SongService {
    private List<Song> list_Song = new ArrayList<Song>();

    public SongService() {

    }

    public List<Song> getList_Song() {
        return list_Song;
    }

    public void setList_Song(List<Song> list_Song) {
        this.list_Song = list_Song;
    }

    public void Upload() {
        String string_temp;
        FileReader File_Upload = null;

        try {
            File_Upload = new FileReader("SONGS.csv");
            BufferedReader br = new BufferedReader(File_Upload);

            while ((string_temp = br.readLine()) != null) {
                Song song_temp = new Song();
                String[] string_temp_array = string_temp.split(";");

                song_temp.setId(UUID.fromString(string_temp_array[0]));
                song_temp.setName(string_temp_array[1]);

                string_temp_array[2] = string_temp_array[2].substring(1, string_temp_array[2].length() - 1).replace(" ",
                        "");

                String[] string_temp_artist = string_temp_array[2].split(",");

                for (int i = 0; i < string_temp_artist.length; i++) {
                    song_temp.getArtists().add(UUID.fromString(string_temp_artist[i]));
                }

                song_temp.setGenre(string_temp_array[3]);
                song_temp.setDuration(Integer.parseInt(string_temp_array[4]));
                song_temp.setAlbum(string_temp_array[5]);

                list_Song.add(song_temp);
            }

            br.close();
        } catch (Exception e) {

        } finally {
            try {
                if (File_Upload != null) {
                    File_Upload.close();
                }
            } catch (IOException e) {

            }
        }

        return;
    }

    public boolean SearchSong(String name) {
        for (Song song_temp : list_Song) {
            if (song_temp.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    public Song getSong_by_name(String name) {
        for (Song song_temp : list_Song) {
            if (song_temp.getName().equalsIgnoreCase(name)) {
                return song_temp;
            }
        }

        return null;
    }
}
