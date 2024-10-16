package database;

import classes.*;

import java.sql.*;

public class DBManager {

    private ConnectDB connection;

    public DBManager(ConnectDB conn) {
        this.connection = conn;
    }

    public ConnectDB getConnection() {
        return connection;
    }

    public void setConnection(ConnectDB connection) {
        this.connection = connection;
    }
//////GENRE TABLE FUNCTIONS////////

    public void insertGenre(Genre genre) {
        String sql = "INSERT INTO Genres (name) VALUES (?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, genre.getName());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedID = generatedKeys.getInt(1);
                genre.setGenreID(generatedID); // Встановлюємо ID в об'єкт Genre
            }
            System.out.println("Genre inserted successfully with ID: " + genre.getGenreID());
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting genre", e);
        }
    }



    public Genre getGenreById(int id) {
        String sql = "SELECT * FROM Genres WHERE id = ?";
        Genre genre = null;

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                genre = new Genre();
                genre.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching genre by ID", e);
        }
        return genre;
    }

    //////AUTHOR TABLE FUNCTIONS////////

    public void insertAuthor(Author author) {
        String sql = "INSERT INTO Authors (name, genre_id, label_id) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, author.getName());
            stmt.setInt(2, author.getGenre().getGenreID());
            stmt.setInt(3, author.getLabel().getLabelID());
            stmt.executeUpdate();

            // Отримання автоінкрементного ID
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedID = generatedKeys.getInt(1);
                author.setAuthorID(generatedID); // Встановлюємо ID в об'єкт Author
            }
            System.out.println("Author inserted successfully with ID: " + author.getAuthorID());
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting author", e);
        }
    }


    public Author getAuthorById(int id) {
        String sql = "SELECT * FROM Authors WHERE id = ?";
        Author author = null;

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                author = new Author();
                author.setAuthorID(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setGenre(getGenreById(rs.getInt("genre_id")));
                author.setLabel(getLabelById(rs.getInt("label_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching author by ID", e);
        }
        return author;
    }

    //////LABEL TABLE FUNCTIONS////////

    public void insertLabel(Label label) {
        String sql = "INSERT INTO Labels (name, location) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, label.getName());
            stmt.setString(2, label.getLocation());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedID = generatedKeys.getInt(1);
                label.setLabelID(generatedID); // Встановлюємо ID в об'єкт Label
            }
            System.out.println("Label inserted successfully with ID: " + label.getLabelID());
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting label", e);
        }
    }


    public Label getLabelById(int id) {
        String sql = "SELECT * FROM Labels WHERE id = ?";
        Label label = null;

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                label = new Label();
                label.setName(rs.getString("name"));
                label.setLocation(rs.getString("location"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching label by ID", e);
        }
        return label;
    }

    //////SONG COLLECTION TABLE FUNCTIONS////////

    public void insertSongCollection(SongCollection collection) {
        String sql = "INSERT INTO SongCollections (name) VALUES (?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, collection.getName());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedID = generatedKeys.getInt(1);
                collection.setCollectionID(generatedID); // Встановлюємо ID в об'єкт SongCollection
            }
            System.out.println("Song collection inserted successfully with ID: " + collection.getCollectionID());
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting song collection", e);
        }
    }


    public SongCollection getSongCollectionById(int id) {
        String sql = "SELECT * FROM SongCollections WHERE id = ?";
        SongCollection collection = null;

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                collection = new SongCollection();
                collection.setCollectionID(rs.getInt("id"));
                collection.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching song collection by ID", e);
        }
        return collection;
    }

    //////SONG TABLE FUNCTIONS////////

    public void insertSong(Song song) {
        String sql = "INSERT INTO Songs (author_id, name, duration_hours, duration_minutes, duration_seconds) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, song.getAuthor().getAuthorID());
            stmt.setString(2, song.getName());
            stmt.setInt(3, (int) song.getHours());
            stmt.setInt(4, (int) song.getMinutes());
            stmt.setInt(5, (int) song.getSeconds());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedID = generatedKeys.getInt(1);
                song.setSongID(generatedID); // Встановлюємо ID в об'єкт Song
            }
            System.out.println("Song inserted successfully with ID: " + song.getSongID());
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting song", e);
        }
    }


    public Song getSongById(int id) {
        String sql = "SELECT * FROM Songs WHERE id = ?";
        Song song = null;

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                song = new Song();
                song.setSongID(rs.getInt("id"));
                song.setName(rs.getString("name"));
                song.setDuration(rs.getInt("duration_minutes"), rs.getInt("duration_seconds"));
                song.setAuthor(getAuthorById(rs.getInt("author_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching song by ID", e);
        }
        return song;
    }

    // Новий метод для оновлення Genre
    public void updateGenre(Genre genre) {
        String sql = "UPDATE Genres SET name = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, genre.getName());
            stmt.setInt(2, genre.getGenreID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Genre updated successfully.");
            } else {
                System.out.println("No genre found with ID: " + genre.getGenreID());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating genre", e);
        }
    }

    // Новий метод для оновлення Label
    public void updateLabel(Label label) {
        String sql = "UPDATE Labels SET name = ?, location = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, label.getName());
            stmt.setString(2, label.getLocation());
            stmt.setInt(3, label.getLabelID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Label updated successfully.");
            } else {
                System.out.println("No label found with ID: " + label.getLabelID());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating label", e);
        }
    }

    // Новий метод для оновлення Author
    public void updateAuthor(Author author) {
        String sql = "UPDATE Authors SET name = ?, genre_id = ?, label_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, author.getName());
            stmt.setInt(2, author.getGenre().getGenreID());
            stmt.setInt(3, author.getLabel().getLabelID());
            stmt.setInt(4, author.getAuthorID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Author updated successfully.");
            } else {
                System.out.println("No author found with ID: " + author.getAuthorID());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating author", e);
        }
    }

    // Оновлений метод для оновлення Song
    public void updateSong(Song song) {
        String sql = "UPDATE Songs SET name = ?, author_id = ?, duration_hours = ?, duration_minutes = ?, duration_seconds = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, song.getName());
            stmt.setInt(2, song.getAuthor().getAuthorID());
            stmt.setInt(3,(int) song.getHours());
            stmt.setInt(4,(int) song.getMinutes());
            stmt.setInt(5,(int) song.getSeconds());
            stmt.setInt(6, song.getSongID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Song updated successfully.");
            } else {
                System.out.println("No song found with ID: " + song.getSongID());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating song", e);
        }
    }

    // Новий метод для оновлення SongCollection
    public void updateSongCollection(SongCollection collection) {
        String sql = "UPDATE SongCollections SET name = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, collection.getName());
            stmt.setInt(2, collection.getCollectionID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Song collection updated successfully.");
            } else {
                System.out.println("No song collection found with ID: " + collection.getCollectionID());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating song collection", e);
        }
    }

    public void deleteSongById(int id) {
        String sql = "DELETE FROM Songs WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Song deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting song", e);
        }
    }

    // Новий метод для видалення Genre
    public void deleteGenreById(int genreId) {
        String sql = "DELETE FROM Genres WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, genreId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Genre deleted successfully.");
            } else {
                System.out.println("No genre found with ID: " + genreId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting genre", e);
        }
    }

    // Новий метод для видалення Label
    public void deleteLabelById(int labelId) {
        String sql = "DELETE FROM Labels WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, labelId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Label deleted successfully.");
            } else {
                System.out.println("No label found with ID: " + labelId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting label", e);
        }
    }

    // Новий метод для видалення Author
    public void deleteAuthorById(int authorId) {
        String sql = "DELETE FROM Authors WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, authorId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Author deleted successfully.");
            } else {
                System.out.println("No author found with ID: " + authorId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting author", e);
        }
    }

    // Новий метод для видалення SongCollection
    public void deleteSongCollectionById(int collectionId) {
        String sql = "DELETE FROM SongCollections WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, collectionId);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Song collection deleted successfully.");
            } else {
                System.out.println("No song collection found with ID: " + collectionId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting song collection", e);
        }
    }

    public void destroyDatabase() {
        String[] tables = {"SongCollectionTracks", "SongCollections", "Songs", "Authors", "Labels", "Genres"};

        try {
            // Disable foreign key constraints
            try (Statement stmt = connection.getConnection().createStatement()) {
                stmt.execute("PRAGMA foreign_keys = OFF;");
            }

            // Drop tables
            for (String table : tables) {
                String sql = "DROP TABLE IF EXISTS " + table;
                try (Statement stmt = connection.getConnection().createStatement()) {
                    stmt.execute(sql);
                    System.out.println("Table " + table + " dropped successfully.");
                }
            }

            // Re-enable foreign key constraints
            try (Statement stmt = connection.getConnection().createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON;");
            }

            System.out.println("Database destroyed successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error destroying database", e);
        }
    }

    public void clearDatabase() {
        String[] tables = {"SongCollectionTracks", "SongCollections", "Songs", "Authors", "Labels", "Genres"};

        try {
            // Disable foreign key constraints
            try (Statement stmt = connection.getConnection().createStatement()) {
                stmt.execute("PRAGMA foreign_keys = OFF;");
            }

            // Clear tables
            for (String table : tables) {
                String sql = "DELETE FROM " + table;
                try (Statement stmt = connection.getConnection().createStatement()) {
                    stmt.execute(sql);
                    System.out.println("Table " + table + " cleared successfully.");
                }
            }

            // Reset auto-increment counters
            for (String table : tables) {
                String sql = "DELETE FROM sqlite_sequence WHERE name='" + table + "'";
                try (Statement stmt = connection.getConnection().createStatement()) {
                    stmt.execute(sql);
                }
            }

            // Re-enable foreign key constraints
            try (Statement stmt = connection.getConnection().createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON;");
            }

            System.out.println("Database cleared successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error clearing database", e);
        }
    }
}
