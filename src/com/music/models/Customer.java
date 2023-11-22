package com.music.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public abstract class Customer {
	protected UUID id;
	protected String user;
	protected String password;
	protected String name;
	protected String lastName;
	protected Integer age;
	protected Set<UUID> followedArtistsIds = new HashSet<>();

	public Customer() {
		setId(UUID.randomUUID());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<UUID> getFollowedArtistsIds() {
		return followedArtistsIds;
	}

	public void setFollowedArtistsIds(Set<UUID> followedArtistsIds) {
		this.followedArtistsIds = followedArtistsIds;
	}

	public abstract void addPlaylist(String name);

	public abstract List<Playlist> getPlaylists();

	public abstract void addPlaylists(List<Playlist> playlists);

	public abstract void removePlaylist(UUID playlistId);

	public abstract void addSongToPlaylist(UUID playlistId, UUID songId) throws MaxSongsInPlayList;

	public abstract void removeSongFromPlaylist(UUID playlistId, UUID songId);

	public abstract List<UUID> getSongsFromPlaylist(UUID playlistId);

	public abstract String toCSV(String delimiter);

	public abstract List<String> playlistToCSVLines(String delimiter);
}
