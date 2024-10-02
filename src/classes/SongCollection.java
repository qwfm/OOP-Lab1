package classes;

import java.util.ArrayList;
import java.util.List;

public class SongCollection {
    private String name;
    private List<MusicTrack> musicTracks;

    public SongCollection() {
    }

    public SongCollection(String name,
                          List<MusicTrack> musicTracks) {
        this.name = name;
        this.musicTracks = musicTracks;
    }

    public void getTotalDuration() {
        int totalSeconds = musicTracks.stream()
                .mapToInt(musicTrack -> (int) (musicTrack.getHours() * 3600 + musicTrack.getMinutes() * 60 + musicTrack.getSeconds()))
                .sum();

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        System.out.printf("%d:%02d:%02d", hours, minutes, seconds);
    }

}
