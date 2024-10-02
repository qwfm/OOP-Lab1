import classes.SongCollection;
import classes.MusicTrack;
import classes.Author;
import classes.Label;

import java.util.ArrayList;
import java.util.List;


public class Main {

    static void testingProgram() {
        Label WarnerMusicGroup = new Label("Warner Music Group", "New York, USA");
        Author Gorillaz = new Author(WarnerMusicGroup, "Gorillaz", "Alternative rock");
        MusicTrack Pacman = new MusicTrack(Gorillaz, "Pac-Man", 3, 51);
        MusicTrack Clint_Eastwood = new MusicTrack(Gorillaz, "Clint Eastwood", 2, 25);
        MusicTrack FeelGoodInc = new MusicTrack(Gorillaz, "Feel-Good Inc.", 2, 49);

        List<MusicTrack> testList = new ArrayList<>();
        testList.add(Pacman); testList.add(Clint_Eastwood); testList.add(FeelGoodInc);
        SongCollection test_Collection = new SongCollection("test", testList);
        test_Collection.getTotalDuration();
    }

    public static void main( String args[] ) {
        testingProgram();
    }
}