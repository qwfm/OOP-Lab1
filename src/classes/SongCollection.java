package classes;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SongCollection {
    private int collectionID;
    private String name;
    private List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }

    public int getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public SongCollection(String name,
                          List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public void totalDuration() {
        int totalSeconds = songs.stream()
                .mapToInt(song -> (int) (song.getHours() * 3600 + song.getMinutes() * 60 + song.getSeconds()))
                .sum();

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        System.out.printf("%d:%02d:%02d", hours, minutes, seconds);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Method to sort songs by the most popular genre first
    public void sortSongsByGenre() {
        // Count the number of songs for each genre
        Map<Genre, Long> genreCountMap = songs.stream()
                .collect(Collectors.groupingBy(musicTrack -> musicTrack.getAuthor().getGenre(), Collectors.counting()));

        // Sort the songs by the genre popularity (most frequent genres first)
        songs.sort(Comparator.comparing((Song track) -> genreCountMap.get(track.getAuthor().getGenre()))
                .reversed());
    }

    public void print() {
        songs.forEach(song -> {
            String title = song.getName();
            String authorName = song.getAuthor().getName();
            String genreName = song.getAuthor().getGenre().getName();

            System.out.printf("Title: %s, Author: %s, Genre: %s, Duration: ", title, authorName, genreName);
            song.duration();
            System.out.println();
        });
    }
}

