package com.music;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import com.music.models.*;
import com.music.services.ArtistService;
import com.music.services.CustomerService;
import com.music.services.FileService;
import com.music.services.SongService;

public class Main {
    public static void main(String[] args) {
        ArtistService Artists = new ArtistService();
        SongService Songs = new SongService();
        CustomerService Customers = new CustomerService();

        Artists.Upload();
        Songs.Upload();
        Customers.Upload();
        Customers.Upload_Playlists();

        Scanner in = new Scanner(System.in);

        int option = 0, option_subMenu = 0;

        do {
            System.out.print("\033[H\033[2J");

            System.out.print("\n\u001B[38;5;220m" +
                    "┌──────────────────────────────────────────────────────────┐\n" +
                    "│                                                          │\n" +
                    "│                            MUSIC                         │\n" +
                    "│                                                          │\n" +
                    "├──────────────────────────────────────────────────────────┤\n\u001B[38;5;231m" +
                    "│                                                          │\n" +
                    "│       0] Salir                                           │\n" +
                    "│       1] Gestion de Artistas                             │\n" +
                    "│       2] Gestion de Canciones                            │\n" +
                    "│       3] Gestion de Usuarios                             │\n" +
                    "│       4] Gestion de Datos                                │\n" +
                    "│                                                          │\n" +
                    "└──────────────────────────────────────────────────────────┘\n" +
                    "               \u001B[38;5;220m■\u001B[38;5;231m Ingresa la opcion que desea: ");
            option = in.nextInt();

            if (option == 0) {
                System.out.println("\n\u001B[38;5;91mFin del programa.\u001B[38;5;231m");
                System.exit(0);
            } else if (option == 1) {
                do {
                    System.out.print("\033[H\033[2J");

                    System.out.print("\n\u001B[38;5;118m" +
                            "┌──────────────────────────────────────────────────────────┐\n" +
                            "│                                                          │\n" +
                            "│                       MUSIC - ARTISTA                    │\n" +
                            "│                                                          │\n" +
                            "├──────────────────────────────────────────────────────────┤\n\u001B[38;5;231m" +
                            "│                                                          │\n" +
                            "│       0] Volver al menu                                  │\n" +
                            "│       1] Nuevo artista                                   │\n" +
                            "│       2] Borrar artista                                  │\n" +
                            "│       3] Listar artistas                                 │\n" +
                            "│                                                          │\n" +
                            "└──────────────────────────────────────────────────────────┘\n" +
                            "               \u001B[38;5;118m■\u001B[38;5;231m Ingresa la opcion que desea: ");
                    option_subMenu = in.nextInt();
                    in.nextLine();

                    if (option_subMenu == 1) {
                        int count = 0;

                        System.out.print("\n\u001B[38;5;118m■\u001B[38;5;231m Cuantos artistas desea agregar: ");
                        count = in.nextInt();
                        in.nextLine();

                        for (int i = 0; i < count; i++) {
                            String name_artist;

                            System.out
                                    .print("\n\u001B[38;5;118m■\u001B[38;5;231m Nombre del artista[" + (i + 1) + "]: ");
                            name_artist = in.nextLine();

                            if (Artists.SearchArtist(name_artist)) {
                                System.out.print("\nEste artista ya esta registrado.");
                                System.out
                                        .println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                                in.nextLine();
                            } else {
                                Artist artist_temp = new Artist();
                                artist_temp.setName(name_artist);

                                Artists.getMapArtists().put(artist_temp.getId(), artist_temp);
                                System.out.println("Agregado!");
                            }
                        }
                    } else if (option_subMenu == 2) {
                        String name_artist;

                        System.out.print("\n\u001B[38;5;118m■\u001B[38;5;231m Nombre del artista: ");
                        name_artist = in.nextLine();

                        if (Artists.SearchArtist(name_artist)) {
                            Artist artist_temp = new Artist();
                            artist_temp = Artists.getArtist_by_name(name_artist);

                            Artists.getMapArtists().remove(artist_temp.getId());
                            System.out.println("Eliminado!");
                        } else {
                            System.out.print("\nEste artista no existe.");
                            System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                            in.nextLine();
                        }
                    } else if (option_subMenu == 3) {
                        System.out.print("┌──────────────────────────────────────────────────────────┐\n");
                        System.out.print("                        LISTA DE ARTISTAS\n\n");

                        for (Map.Entry<UUID, Artist> data : Artists.getMapArtists().entrySet()) {
                            System.out
                                    .println("\n\u001B[38;5;118m      ■\u001B[38;5;231m " + data.getValue().getName());
                        }

                        System.out.print("└──────────────────────────────────────────────────────────┘\n");

                        System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                        in.nextLine();
                    }
                } while (option_subMenu != 0);
            } else if (option == 2) {
                do {
                    System.out.print("\033[H\033[2J");

                    System.out.print("\n\u001B[38;5;51m" +
                            "┌──────────────────────────────────────────────────────────┐\n" +
                            "│                                                          │\n" +
                            "│                     MUSIC - CANCIONES                    │\n" +
                            "│                                                          │\n" +
                            "├──────────────────────────────────────────────────────────┤\n\u001B[38;5;231m" +
                            "│                                                          │\n" +
                            "│       0] Volver al menu                                  │\n" +
                            "│       1] Nueva cancion                                   │\n" +
                            "│       2] Eliminar cancion                                │\n" +
                            "│       3] Nuevo artista en una cancion                    │\n" +
                            "│       4] Quitar artista de una cancion                   │\n" +
                            "│       5] Listar canciones                                │\n" +
                            "│                                                          │\n" +
                            "└──────────────────────────────────────────────────────────┘\n" +
                            "               \u001B[38;5;51m■\u001B[38;5;231m Ingresa la opcion que desea: ");
                    option_subMenu = in.nextInt();
                    in.nextLine();

                    if (option_subMenu == 1) {
                        int count;

                        System.out.print("\n\u001B[38;5;51m■\u001B[38;5;231m Cuantas canciones va agregar: ");
                        count = in.nextInt();
                        in.nextLine();

                        for (int i = 0; i < count; i++) {
                            Artist artist_temp = null;
                            String name_song, artist_song, genre_song, album_song;
                            int duration_song;

                            System.out.print(
                                    "\n\u001B[38;5;51m■\u001B[38;5;231m Nombre de la cancion[" + (i + 1) + "]: ");
                            name_song = in.nextLine();

                            do {
                                System.out.print(
                                        "\n\u001B[38;5;51m■\u001B[38;5;231m Nombre del artista[" + (i + 1) + "]: ");
                                artist_song = in.nextLine();

                                if (!Artists.SearchArtist(artist_song)) {
                                    System.out.print(
                                            "\n\u001B[38;5;51m■\u001B[38;5;231m Artista invalido \n\u001B[38;5;118m■\u001B[38;5;231m");
                                } else {
                                    artist_temp = Artists.getArtist_by_name(artist_song);
                                }
                            } while (Artists.SearchArtist(artist_song) == false);

                            System.out.print(
                                    "\n\u001B[38;5;51m■\u001B[38;5;231m Genero de la cancion[" + (i + 1) + "]: ");
                            genre_song = in.nextLine();

                            System.out.print(
                                    "\n\u001B[38;5;51m■\u001B[38;5;231m duracion de la cancion[" + (i + 1) + "]: ");
                            duration_song = in.nextInt();
                            in.nextLine();

                            System.out.print("\n\u001B[38;5;51m■\u001B[38;5;231m Nombre del Album[" + (i + 1) + "]: ");
                            album_song = in.nextLine();

                            Song song_temp = new Song();

                            song_temp.setName(name_song);
                            song_temp.setGenre(genre_song);
                            song_temp.setDuration(duration_song);
                            song_temp.setAlbum(album_song);
                            song_temp.getArtists().add(artist_temp.getId());

                            Songs.getList_Song().add(song_temp);
                            System.out.println("Agregado!");
                        }
                    } else if (option_subMenu == 2) {
                        String name_song;

                        System.out.print("\n\u001B[38;5;51m■\u001B[38;5;231m Nombre de la cancion: ");
                        name_song = in.nextLine();

                        if (Songs.SearchSong(name_song)) {
                            Songs.getList_Song().remove(Songs.getSong_by_name(name_song));
                            System.out.println("Eliminada!");
                        } else {
                            System.out.print("\nEste cancion no existe.");
                            System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                            in.nextLine();
                        }
                    } else if (option_subMenu == 3) {
                        String name_song;

                        System.out.print("\n\u001B[38;5;51m■\u001B[38;5;231m Nombre de la cancion: ");
                        name_song = in.nextLine();

                        if (Songs.SearchSong(name_song)) {
                            String name_artist;

                            System.out.print("\n\u001B[38;5;51m■\u001B[38;5;231m Nombre del artista: ");
                            name_artist = in.nextLine();

                            if (Artists.SearchArtist(name_artist)) {
                                Song song_temp = new Song();
                                song_temp = Songs.getSong_by_name(name_song);
                                Songs.getList_Song().remove(song_temp);

                                Artist artist_temp = new Artist();
                                artist_temp = Artists.getArtist_by_name(name_artist);

                                song_temp.getArtists().add(artist_temp.getId());
                                Songs.getList_Song().add(song_temp);

                                System.out.println("Agregado!");
                            } else {
                                System.out.print("\nEste artista no existe.");
                                System.out
                                        .println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                                in.nextLine();
                            }
                        } else {
                            System.out.print("\nEste cancion no existe.");
                            System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                            in.nextLine();
                        }
                    } else if (option_subMenu == 4) {
                        String name_song;

                        System.out.print("\n\u001B[38;5;51m■\u001B[38;5;231m Nombre de la cancion: ");
                        name_song = in.nextLine();

                        if (Songs.SearchSong(name_song)) {
                            String name_artist;

                            System.out.print("\n\u001B[38;5;51m■\u001B[38;5;231m Nombre del artista: ");
                            name_artist = in.nextLine();

                            if (Artists.SearchArtist(name_artist)) {
                                Song song_temp = new Song();
                                song_temp = Songs.getSong_by_name(name_song);
                                Songs.getList_Song().remove(song_temp);

                                Artist artist_temp = new Artist();
                                artist_temp = Artists.getArtist_by_name(name_artist);

                                song_temp.getArtists().remove(artist_temp.getId());
                                Songs.getList_Song().add(song_temp);

                                System.out.println("Eliminado!");
                            } else {
                                System.out.print("\nEste artista no existe.");
                                System.out
                                        .println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                                in.nextLine();
                            }
                        } else {
                            System.out.print("\nEste cancion no existe.");
                            System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                            in.nextLine();
                        }
                    } else if (option_subMenu == 5) {
                        System.out.print("┌──────────────────────────────────────────────────────────┐\n");
                        System.out.print("                        LISTA DE CANCIONES\n\n");

                        for (Song song_temp : Songs.getList_Song()) {
                            System.out.println("\n\u001B[38;5;51m      ■\u001B[38;5;231m " + song_temp.getName());
                        }

                        System.out.print("└──────────────────────────────────────────────────────────┘\n");

                        System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                        in.nextLine();
                    }
                } while (option_subMenu != 0);
            } else if (option == 3) {
                do {
                    System.out.print("\033[H\033[2J");

                    System.out.print("\n\u001B[38;5;84m" +
                            "┌──────────────────────────────────────────────────────────┐\n" +
                            "│                                                          │\n" +
                            "│                      MUSIC - USUARIOS                    │\n" +
                            "│                                                          │\n" +
                            "├──────────────────────────────────────────────────────────┤\n\u001B[38;5;231m" +
                            "│                                                          │\n" +
                            "│       0] Volver al menu                                  │\n" +
                            "│       1] Nuevo usuario                                   │\n" +
                            "│       2] Eliminar usuario                                │\n" +
                            "│       3] Nueva Playlist                                  │\n" +
                            "│       4] Nueva cancion en Playlist                       │\n" +
                            "│       5] Nuevo seguidor                                  │\n" +
                            "│       6] Listar usuarios                                 │\n" +
                            "│                                                          │\n" +
                            "└──────────────────────────────────────────────────────────┘\n" +
                            "               \u001B[38;5;84m■\u001B[38;5;231m Ingresa la opcion que desea: ");
                    option_subMenu = in.nextInt();
                    in.nextLine();

                    if (option_subMenu == 1) {
                        int count;

                        System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Cuantos usuarios va agregar: ");
                        count = in.nextInt();
                        in.nextLine();

                        for (int i = 0; i < count; i++) {
                            Customer customer_temp = null;
                            String type_user, user_user, pwd_user, name_user, lastname_user, playlist_user, follow_user;
                            int age_user;

                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Tipo de usuario[" + (i + 1) + "].");
                            System.out.print(" Regular[R] o Premium[P]: ");
                            type_user = in.nextLine();

                            if (type_user.equalsIgnoreCase("P")) {
                                customer_temp = new PremiumCustomer();
                            } else if (type_user.equalsIgnoreCase("R")) {
                                customer_temp = new RegularCustomer();
                            } else {
                                System.out.print("\nTipo no reconocido!.");
                                System.out
                                        .println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                                in.nextLine();
                                continue;
                            }

                            do {
                                System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Username [" + (i + 1) + "]: ");
                                user_user = in.nextLine();
                            } while (!user_user.matches("^[a-zA-Z][a-zA-Z0-9_]{7,29}$"));

                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Clave [" + (i + 1) + "]: ");
                            pwd_user = in.nextLine();

                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Su nombre [" + (i + 1) + "]: ");
                            name_user = in.nextLine();

                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Su apellido [" + (i + 1) + "]: ");
                            lastname_user = in.nextLine();

                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Edad [" + (i + 1) + "]: ");
                            age_user = in.nextInt();
                            in.nextLine();

                            customer_temp.setUser(user_user);
                            customer_temp.setPassword(pwd_user);
                            customer_temp.setName(name_user);
                            customer_temp.setLastName(lastname_user);
                            customer_temp.setAge(age_user);

                            if (customer_temp instanceof PremiumCustomer) {
                                System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Nombre de su primer Playlist ["
                                        + (i + 1) + "]: ");
                                playlist_user = in.nextLine();

                                customer_temp.addPlaylist(playlist_user);
                            }

                            Customers.getList_Customers().add(customer_temp);
                            System.out.println("Agregado!");
                        }
                    } else if (option_subMenu == 2) {
                        String type_user, username;

                        System.out.println("\n\u001B[38;5;84m■\u001B[38;5;231m Tipo de usuario.");
                        System.out.print(" Regular[R] o Premium[P]: ");
                        type_user = in.nextLine();

                        if (type_user.equalsIgnoreCase("P")) {
                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Username: ");
                            username = in.nextLine();

                            for (Customer customer_temp : Customers.getList_Customers()) {
                                if ((customer_temp.getUser().equalsIgnoreCase(username))
                                        && (customer_temp instanceof PremiumCustomer)) {
                                    Customers.getList_Customers().remove(customer_temp);
                                    System.out.println("Eliminado!");
                                    break;
                                }
                            }
                        } else if (type_user.equalsIgnoreCase("R")) {
                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Username: ");
                            username = in.nextLine();

                            for (Customer customer_temp : Customers.getList_Customers()) {
                                if ((customer_temp.getUser().equalsIgnoreCase(username))
                                        && (customer_temp instanceof RegularCustomer)) {
                                    Customers.getList_Customers().remove(customer_temp);
                                    System.out.println("Eliminado!");
                                    break;
                                }
                            }
                        } else {
                            System.out.print("\nTipo no reconocido!.");
                            System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                            in.nextLine();
                            continue;
                        }
                    } else if (option_subMenu == 3) {
                        String type_user, username;

                        System.out.println("\n\u001B[38;5;84m■\u001B[38;5;231m Tipo de usuario.");
                        System.out.print(" Regular[R] o Premium[P]: ");
                        type_user = in.nextLine();

                        if (type_user.equalsIgnoreCase("P")) {
                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Username: ");
                            username = in.nextLine();

                            for (Customer customer_temp : Customers.getList_Customers()) {
                                if ((customer_temp.getUser().equalsIgnoreCase(username))
                                        && (customer_temp instanceof PremiumCustomer)) {
                                    String play_temp;

                                    System.out.println("\n\u001B[38;5;84m■\u001B[38;5;231m Nombre de la playlist: ");
                                    play_temp = in.nextLine();

                                    customer_temp.addPlaylist(play_temp);

                                    break;
                                }
                            }
                        } else if (type_user.equalsIgnoreCase("R")) {
                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Username: ");
                            username = in.nextLine();

                            for (Customer customer_temp : Customers.getList_Customers()) {
                                if ((customer_temp.getUser().equalsIgnoreCase(username))
                                        && (customer_temp instanceof RegularCustomer)) {
                                    String play_temp;

                                    System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Nombre de la playlist: ");
                                    play_temp = in.nextLine();

                                    customer_temp.addPlaylist(play_temp);

                                    break;
                                }
                            }
                        } else {
                            System.out.print("\nTipo no reconocido!.");
                            System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                            in.nextLine();
                            continue;
                        }
                    } else if (option_subMenu == 4) {
                        String type_user, username;

                        System.out.println("\n\u001B[38;5;84m■\u001B[38;5;231m Tipo de usuario.");
                        System.out.print(" Regular[R] o Premium[P]: ");
                        type_user = in.nextLine();

                        if (type_user.equalsIgnoreCase("P")) {
                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Username: ");
                            username = in.nextLine();

                            for (Customer customer_temp : Customers.getList_Customers()) {
                                if ((customer_temp.getUser().equalsIgnoreCase(username))
                                        && (customer_temp instanceof PremiumCustomer)) {
                                    String play_temp_string;

                                    System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Nombre de la playlist: ");
                                    play_temp_string = in.nextLine();

                                    List<Playlist> play_temp_list = customer_temp.getPlaylists();

                                    if (play_temp_list != null) {
                                        for (Playlist play_temp : play_temp_list) {
                                            if (play_temp.getName().equalsIgnoreCase(play_temp_string)) {
                                                String song_temp;

                                                System.out.print(
                                                        "\n\u001B[38;5;84m■\u001B[38;5;231m Nombre de la cancion: ");
                                                song_temp = in.nextLine();

                                                if (Songs.SearchSong(song_temp)) {
                                                    customer_temp.addSongToPlaylist(play_temp.getId(),
                                                            Songs.getSong_by_name(song_temp).getId());
                                                }
                                            }
                                        }
                                    }

                                    break;
                                }
                            }
                        } else if (type_user.equalsIgnoreCase("R")) {
                            System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Username: ");
                            username = in.nextLine();

                            for (Customer customer_temp : Customers.getList_Customers()) {
                                if ((customer_temp.getUser().equalsIgnoreCase(username))
                                        && (customer_temp instanceof RegularCustomer)) {
                                    List<Playlist> play_temp_list = customer_temp.getPlaylists();

                                    if (play_temp_list != null) {
                                        String song_temp;

                                        System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Nombre de la cancion: ");
                                        song_temp = in.nextLine();

                                        if (Songs.SearchSong(song_temp)) {
                                            customer_temp.addSongToPlaylist(play_temp_list.get(0).getId(),
                                                    Songs.getSong_by_name(song_temp).getId());
                                        }
                                    }

                                    break;
                                }
                            }
                        } else {
                            System.out.print("\nTipo no reconocido!.");
                            System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                            in.nextLine();
                            continue;
                        }
                    } else if (option_subMenu == 5) {
                        String username;

                        System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Username: ");
                        username = in.nextLine();

                        for (Customer customer_temp : Customers.getList_Customers()) {
                            if (customer_temp.getUser().equalsIgnoreCase(username)) {
                                String artist_string;

                                System.out.print("\n\u001B[38;5;84m■\u001B[38;5;231m Nombre del artista: ");
                                artist_string = in.nextLine();

                                if (Artists.SearchArtist(artist_string)) {
                                    customer_temp.getFollowedArtistsIds()
                                            .add(Artists.getArtist_by_name(artist_string).getId());
                                } else {
                                    System.out.print("\nNo se hallo el artista!.");
                                    System.out.println(
                                            "\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                                    in.nextLine();
                                }

                                System.out.println("Follow!!");
                                break;
                            }
                        }
                    } else if (option_subMenu == 6) {
                        String type_user;

                        System.out.println("\n\u001B[38;5;84m■\u001B[38;5;231m Tipo de usuario.");
                        System.out.print(" Regular[R] o Premium[P]: ");
                        type_user = in.nextLine();

                        if (type_user.equalsIgnoreCase("P")) {
                            System.out.print("┌──────────────────────────────────────────────────────────┐\n");
                            System.out.print("                        LISTA DE PREMIUM\n\n");

                            for (Customer customer_temp : Customers.getList_Customers()) {
                                if (customer_temp instanceof PremiumCustomer) {
                                    System.out.print("\n\u001B[38;5;84m      ■\u001B[38;5;231m "
                                            + customer_temp.getUser() + " -> ");

                                    for (Playlist play_temp : customer_temp.getPlaylists()) {
                                        System.out.print(play_temp.getName() + ", ");
                                    }
                                    System.out.println("");
                                }
                            }

                            System.out.print("└──────────────────────────────────────────────────────────┘\n");

                            System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                            in.nextLine();
                        } else if (type_user.equalsIgnoreCase("R")) {
                            System.out.print("┌──────────────────────────────────────────────────────────┐\n");
                            System.out.print("                        LISTA DE REGULAR\n\n");

                            for (Customer customer_temp : Customers.getList_Customers()) {
                                if (customer_temp instanceof RegularCustomer) {
                                    System.out.println(
                                            "\n\u001B[38;5;84m      ■\u001B[38;5;231m " + customer_temp.getUser()
                                                    + " -> " + customer_temp.getPlaylists().get(0).getName());
                                }
                            }

                            System.out.print("└──────────────────────────────────────────────────────────┘\n");

                            System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                            in.nextLine();
                        } else {
                            System.out.print("\nTipo no reconocido!.");
                            System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                            in.nextLine();
                            continue;
                        }
                    }

                } while (option_subMenu != 0);
            } else if (option == 4) {
                do {
                    System.out.print("\033[H\033[2J");
                    FileService fileManage = new FileService();
                    fileManage.setMapArtists(Artists.getMapArtists());
                    fileManage.setList_Song(Songs.getList_Song());
                    fileManage.setList_Customers(Customers.getList_Customers());

                    System.out.print("\n\u001B[38;5;30m" +
                            "┌──────────────────────────────────────────────────────────┐\n" +
                            "│                                                          │\n" +
                            "│                      MUSIC - DATOS                       │\n" +
                            "│                                                          │\n" +
                            "├──────────────────────────────────────────────────────────┤\n\u001B[38;5;231m" +
                            "│                                                          │\n" +
                            "│       0] Volver al menu                                  │\n" +
                            "│       1] Guardar archivos                                │\n" +
                            "│       2] Reporte seguidores                              │\n" +
                            "│       3] Reporte populares                               │\n" +
                            "│       4] Reporte canciones en toda playlist              │\n" +
                            "│                                                          │\n" +
                            "└──────────────────────────────────────────────────────────┘\n" +
                            "               \u001B[38;5;30m■\u001B[38;5;231m Ingresa la opcion que desea: ");
                    option_subMenu = in.nextInt();
                    in.nextLine();

                    if (option_subMenu == 1) {
                        fileManage.SaveAll();
                    } else if (option_subMenu == 2) {
                        Map<UUID, Integer> followers_temp = fileManage.Followers();

                        System.out.print("┌──────────────────────────────────────────────────────────┐\n");
                        System.out.print("                  LISTA DE SEGUIDORES\n\n");

                        for (Map.Entry<UUID, Integer> data : followers_temp.entrySet()) {
                            Artist artist_temp = Artists.getMapArtists().get(data.getKey());

                            System.out.println("\n\u001B[38;5;30m      ■\u001B[38;5;231m " + artist_temp.getName()
                                    + " -> " + data.getValue());
                        }

                        System.out.print("└──────────────────────────────────────────────────────────┘\n");

                        System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                        in.nextLine();
                    } else if (option_subMenu == 3) {
                        Map<UUID, Float> popularity_temp = fileManage.Popularity();

                        System.out.print("┌──────────────────────────────────────────────────────────┐\n");
                        System.out.print("                  LISTA DE POPULARIDAD\n\n");

                        for (Map.Entry<UUID, Float> data : popularity_temp.entrySet()) {
                            Artist artist_temp = Artists.getMapArtists().get(data.getKey());

                            System.out.println("\n\u001B[38;5;30m      ■\u001B[38;5;231m " + artist_temp.getName()
                                    + " -> " + data.getValue());
                        }

                        System.out.print("└──────────────────────────────────────────────────────────┘\n");

                        System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                        in.nextLine();
                    } else if (option_subMenu == 4) {
                        List<UUID> song_inAll = fileManage.Songs_all_PlayList();

                        System.out.print("┌──────────────────────────────────────────────────────────┐\n");
                        System.out.print("                 LISTA DE CANCIONES EN TODO\n\n");

                        for (UUID song_id_temp : song_inAll) {
                            for (Song song_temp : Songs.getList_Song()) {
                                if (song_id_temp.equals(song_temp.getId())) {
                                    System.out
                                            .println("\n\u001B[38;5;30m      ■\u001B[38;5;231m " + song_temp.getName());
                                }
                            }
                        }

                        System.out.print("└──────────────────────────────────────────────────────────┘\n");

                        System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                        in.nextLine();
                    }
                } while (option_subMenu != 0);
            } else {
                System.out.print("\nOpcion no determinada!.");
                System.out.println("\n\u001B[38;5;220mPresiona Enter para continuar...\u001B[38;5;231m");
                in.nextLine();
            }
        } while (option != 0);

        in.close();
    }
}
