package com.music.services;

import com.music.models.Customer;
import com.music.models.Playlist;
import com.music.models.PremiumCustomer;
import com.music.models.RegularCustomer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CustomerService {
	private List<Customer> list_Customers = new ArrayList<Customer>();

	public CustomerService() {

	}

	public List<Customer> getList_Customers() {
		return list_Customers;
	}

	public void setList_Customers(List<Customer> list_Customers) {
		this.list_Customers = list_Customers;
	}

	public void Upload() {
		String string_temp;
		FileReader File_Upload = null;

		try {
			File_Upload = new FileReader("CUSTOMERS.csv");
			BufferedReader br = new BufferedReader(File_Upload);

			while ((string_temp = br.readLine()) != null) {
				String[] string_temp_array = string_temp.split(";");
				Customer customer_temp = null;

				if (string_temp_array[0].equalsIgnoreCase("Premium")) {
					customer_temp = new PremiumCustomer();
				} else {
					customer_temp = new RegularCustomer();
				}

				customer_temp.setId(UUID.fromString(string_temp_array[1]));
				customer_temp.setUser(string_temp_array[2]);
				customer_temp.setPassword(string_temp_array[3]);
				customer_temp.setName(string_temp_array[4]);
				customer_temp.setLastName(string_temp_array[5]);
				customer_temp.setAge(Integer.parseInt(string_temp_array[6]));

				if (!string_temp_array[7].equalsIgnoreCase("{}")) {
					string_temp_array[7] = string_temp_array[7].substring(1, string_temp_array[7].length() - 1)
							.replace(" ", "");
					String[] string_temp_artist = string_temp_array[7].split(",");
					Set<UUID> followed_temp = new HashSet<>();

					for (int i = 0; i < string_temp_artist.length; i++) {

						followed_temp.add(UUID.fromString(string_temp_artist[i]));
					}

					customer_temp.setFollowedArtistsIds(followed_temp);
				}

				list_Customers.add(customer_temp);
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

	public void Upload_Playlists() {
		String string_temp;
		FileReader File_Upload = null;

		try {
			File_Upload = new FileReader("PLAYLISTS.csv");
			BufferedReader br = new BufferedReader(File_Upload);

			while ((string_temp = br.readLine()) != null) {
				String[] string_temp_array = string_temp.split(";");
				Playlist play_temp = new Playlist();

				play_temp.setId(UUID.fromString(string_temp_array[0]));
				play_temp.setName(string_temp_array[1]);

				if (!string_temp_array[3].equalsIgnoreCase("{}")) {
					string_temp_array[3] = string_temp_array[3].substring(1, string_temp_array[3].length() - 1)
							.replace(" ", "");
					String[] string_temp_artist = string_temp_array[3].split(",");
					List<UUID> songsIds_temp = new ArrayList<UUID>();

					for (int i = 0; i < string_temp_artist.length; i++) {

						songsIds_temp.add(UUID.fromString(string_temp_artist[i]));
					}

					play_temp.setSongsIds(songsIds_temp);
				}

				for (Customer customer_temp : list_Customers) {
					if (customer_temp.getId().equals(UUID.fromString(string_temp_array[2]))) {
						List<Playlist> list_temp_play = new ArrayList<>();
						list_temp_play.add(play_temp);

						customer_temp.addPlaylists(list_temp_play);
					}
				}
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
}
