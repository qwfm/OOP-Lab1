package database;

import java.sql.*;


public class DB_Initializer {

    private Connection connection;

    public DB_Initializer() {}

    public DB_Initializer(Connection connection) {
        this.connection = connection;
    }

    public void createTables() {
        try (Statement stmt = connection.createStatement()) {

            String genreTable = "CREATE TABLE IF NOT EXISTS Genres ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL"
                    + ");";
            stmt.execute(genreTable);

            String labelTable = "CREATE TABLE IF NOT EXISTS Labels ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "location TEXT"
                    + ");";
            stmt.execute(labelTable);

            String authorTable = "CREATE TABLE IF NOT EXISTS Authors ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "genre_id INTEGER,"
                    + "label_id INTEGER,"
                    + "FOREIGN KEY (genre_id) REFERENCES Genre(id),"
                    + "FOREIGN KEY (label_id) REFERENCES Label(id)"
                    + ");";
            stmt.execute(authorTable);

            String songTable = "CREATE TABLE IF NOT EXISTS Songs ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "author_id INTEGER,"
                    + "name TEXT NOT NULL,"
                    + "duration_hours INTEGER DEFAULT 0,"
                    + "duration_minutes INTEGER DEFAULT 0,"
                    + "duration_seconds INTEGER DEFAULT 0,"
                    + "FOREIGN KEY (author_id) REFERENCES Author(id)"
                    + ");";
            stmt.execute(songTable);

            String songCollectionTable = "CREATE TABLE IF NOT EXISTS SongCollections ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL"
                    + ");";
            stmt.execute(songCollectionTable);

            String songCollectionTracksTable = "CREATE TABLE IF NOT EXISTS SongCollectionTracks ("
                    + "collection_id INTEGER,"
                    + "song_id INTEGER,"
                    + "FOREIGN KEY (collection_id) REFERENCES SongCollection(id),"
                    + "FOREIGN KEY (song_id) REFERENCES Song(id),"
                    + "PRIMARY KEY (collection_id, song_id)"
                    + ");";
            stmt.execute(songCollectionTracksTable);

            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            System.out.println("Error while creating tables: " + e.getMessage());
        }
    }
}

