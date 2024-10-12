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
            stmt.setFloat(3, song.getHours());
            stmt.setFloat(4, song.getMinutes());
            stmt.setFloat(5, song.getSeconds());
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
                song.setDuration(rs.getFloat("duration_minutes"), rs.getFloat("duration_seconds"));
                song.setAuthor(getAuthorById(rs.getInt("author_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching song by ID", e);
        }
        return song;
    }

    public void updateSong(Song song) {
        String sql = "UPDATE Songs SET name = ?, duration_hours = ?, duration_minutes = ?, duration_seconds = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {

            stmt.setString(1, song.getName());
            stmt.setFloat(2, song.getHours());
            stmt.setFloat(3, song.getMinutes());
            stmt.setFloat(4, song.getSeconds());
            stmt.setInt(5, song.getSongID());

            stmt.executeUpdate();
            System.out.println("Song updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating song", e);
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
}
