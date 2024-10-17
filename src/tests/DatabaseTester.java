package tests;

import classes.*;
import database.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTester {
    private static ConnectDB connection;
    private static DBManager dbManager;

    @Test
    public void test() throws SQLException {
        try{Class.forName("org.sqlite.JDBC");}catch(ClassNotFoundException e){System.out.print("");}

        initializeDatabase();
        testGenre();
        testLabel();
        testAuthor();
        testSong();
        testSongCollection();
        closeConnection();
        //dbManager.clearDatabase();
    }

    private static void initializeDatabase() throws SQLException {
        connection = new ConnectDB();
        dbManager = new DBManager(connection);
        DB_Initializer initializer = new DB_Initializer(connection.getConnection());
        initializer.createTables();
        System.out.println("Database initialized.");
    }

    private static void testGenre() {
        System.out.println("\nTesting Genre...");
        String[] genreNames = {"Rock", "Pop", "Jazz"};
        for (String genreName : genreNames) {
            Genre genre = new Genre(genreName);
            dbManager.insertGenre(genre);
            genre.setName(genreName + " Music");
            dbManager.updateGenre(genre);
        }
        //dbManager.deleteGenreById(genreNames.length);
    }

    private static void testLabel() {
        System.out.println("\nTesting Label...");
        String[] labelNames = {"Columbia Records", "Universal Music", "Sony Music"};
        String[] locations = {"New York", "California", "Tokyo"};
        for (int i = 0; i < labelNames.length; i++) {
            Label label = new Label(labelNames[i], locations[i]);
            dbManager.insertLabel(label);
            label.setLocation(locations[(i + 1) % locations.length]);
            dbManager.updateLabel(label);
        }
        //dbManager.deleteLabelById(labelNames.length);
    }

    private static void testAuthor() {
        System.out.println("\nTesting Author...");
        String[] authorNames = {"Michael Jackson", "The Beatles", "Madonna"};
        for (int i = 0; i < authorNames.length; i++) {
            Genre genre = dbManager.getGenreById(i + 1);
            Label label = dbManager.getLabelById(i + 1);
            Author author = new Author(label, authorNames[i], genre);
            dbManager.insertAuthor(author);
            author.setName("The Great " + authorNames[i]);
            dbManager.updateAuthor(author);
        }
        //dbManager.deleteAuthorById(authorNames.length);
    }

    private static void testSong() {
        System.out.println("\nTesting Song...");
        String[] songNames = {"Billie Jean", "Hey Jude", "Like a Prayer", "Thriller", "Yesterday"};
        int[][] durations = {{4, 54}, {7, 11}, {5, 39}, {5, 57}, {2, 4}};
        for (int i = 0; i < songNames.length-1; i++) {
            Author author = dbManager.getAuthorById((i % 3) + 1);
            Song song = new Song(author, songNames[i], durations[i][0], durations[i][1]);
            dbManager.insertSong(song);
            song.setName(songNames[i] + " (Remix)");
            song.setDuration(durations[i][0] + 1, durations[i][1]);
            dbManager.updateSong(song);
        }
        dbManager.deleteSongById(songNames.length);
    }

    private static void testSongCollection() {
        System.out.println("\nTesting SongCollection...");
        List<Song> songs = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Song song = dbManager.getSongById(i);
            if (song != null) {
                songs.add(song);
                dbManager.addSongToCollection(1, song.getSongID());
            }
        }
        SongCollection collection = new SongCollection("Greatest Hits", songs);
        dbManager.insertSongCollection(collection);
        collection.setName("Ultimate Greatest Hits");
        dbManager.updateSongCollection(collection);
        //dbManager.deleteSongCollectionById(collection.getCollectionID());
    }

    private static void closeConnection() {
        connection.closeConnection();
        System.out.println("\nDatabase connection closed.");
    }
}