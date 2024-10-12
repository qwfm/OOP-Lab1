import classes.*;
import database.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException {

        try{Class.forName("org.sqlite.JDBC");}catch(ClassNotFoundException e){System.out.print("");}

        ConnectDB connectDB = new ConnectDB();
        DB_Initializer dbInitializer = new DB_Initializer(connectDB.getConnection());
        dbInitializer.createTables();
        DBManager dbManager = new DBManager(connectDB);

        Genre rockGenre = new Genre("Rock");
        dbManager.insertGenre(rockGenre);

        Label label = new Label("Universal Music", "USA");
        dbManager.insertLabel(label);

        Author author = new Author(label, "The Beatles", rockGenre);
        dbManager.insertAuthor(author);

        Song song = new Song(author, "Hey Jude", 3, 20);
        dbManager.insertSong(song);

        SongCollection collection = new SongCollection("Classic Hits", List.of(song));
        dbManager.insertSongCollection(collection);

        System.out.println("Inserted Data:");

        Genre retrievedGenre = dbManager.getGenreById(1);
        System.out.println("Genre: " + retrievedGenre.getName());

        Label retrievedLabel = dbManager.getLabelById(1);
        System.out.println("Label: " + retrievedLabel.getName() + ", Location: " + retrievedLabel.getLocation());

        Author retrievedAuthor = dbManager.getAuthorById(1);
        String authorpenis = retrievedAuthor.getName();
        String genrepenis = retrievedAuthor.getGenre().getName();
        System.out.println("Author: " + authorpenis + ", Genre: " + genrepenis);

        Song retrievedSong = dbManager.getSongById(1);
        System.out.printf("Song: %s, Duration: ", retrievedSong.getName());
        retrievedSong.duration(); System.out.println();

        SongCollection retrievedCollection = dbManager.getSongCollectionById(1);
        System.out.println("Song Collection: " + retrievedCollection.getName());
    }
}