package database;

import classes.*;

import java.sql.*;

public class SongTable {

    private ConnectDB connection;

    public SongTable(ConnectDB connection) {
        this.connection = connection;
    }

    public void insertSong(Song song) {
        String sql = "INSERT INTO Songs (author_id,name,duration_hours,duration_minutes,duration_seconds) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, song.getAuthor().getAuthorID());
            pstmt.setString(2, song.getName());
            pstmt.setFloat(3, song.getHours());
            pstmt.setFloat(4, song.getMinutes());
            pstmt.setFloat(5, song.getMinutes());
        }
        catch (SQLException e) {
            throw new RuntimeException("Error inserting song", e);
        }
    }

    public Song getSongById(int id) {
        String sql = "SELECT * FROM Songs WHERE id = ?";
        Song song = null;

        try (Connection conn = connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                song = new Song();
                song.setSongID(rs.getInt("id"));
                song.setName(rs.getString("name"));
                song.setDuration(rs.getFloat("duration_minutes"), rs.getFloat("duration_seconds"));
                //TODO: song.setAuthor(getAuthorById(rs.getInt("author_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching song by ID", e);
        }
        return song;
    }

    public void updateSong(Song song) {
        String sql = "UPDATE Songs SET name = ?, duration_hours = ?, duration_minutes = ?, duration_seconds = ? WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, song.getName());
            pstmt.setFloat(2, song.getHours());
            pstmt.setFloat(3, song.getMinutes());
            pstmt.setFloat(4, song.getSeconds());
            pstmt.setInt(5, song.getSongID());

            pstmt.executeUpdate();
            System.out.println("Song updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error updating song", e);
        }
    }

    public void deleteSongById(int id) {
        String sql = "DELETE FROM Songs WHERE id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Song deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting song", e);
        }
    }
}
