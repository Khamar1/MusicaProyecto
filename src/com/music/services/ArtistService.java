package com.music.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.music.models.Artist;

public class ArtistService {
	private Map<UUID, Artist> mapArtists = new HashMap<>();

	public ArtistService() {

	}

	public Map<UUID, Artist> getMapArtists() {
		return mapArtists;
	}

	public void setMapArtists(Map<UUID, Artist> mapArtists) {
		this.mapArtists = mapArtists;
	}

	public void Upload() {
		String string_temp;
		FileReader File_Upload = null;

		try {
			File_Upload = new FileReader("ARTISTS.csv");
			BufferedReader br = new BufferedReader(File_Upload);

			while ((string_temp = br.readLine()) != null) {
				Artist artist_temp = new Artist();
				String[] string_temp_array = string_temp.split(";");

				artist_temp.setId(UUID.fromString(string_temp_array[0]));
				artist_temp.setName(string_temp_array[1]);

				mapArtists.put(UUID.fromString(string_temp_array[0]), artist_temp);
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

	public boolean SearchArtist(String name) {
		for (Map.Entry<UUID, Artist> data : mapArtists.entrySet()) {
			if (data.getValue().getName().equalsIgnoreCase(name)) {
				return true;
			}
		}

		return false;
	}

	public Artist getArtist_by_name(String name) {
		for (Map.Entry<UUID, Artist> data : mapArtists.entrySet()) {
			if (data.getValue().getName().equalsIgnoreCase(name)) {
				return data.getValue();
			}
		}

		return null;
	}
}
