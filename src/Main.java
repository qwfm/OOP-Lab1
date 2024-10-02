import classes.Author;
import classes.Label;
import classes.MusicTrack;

public class Main {

    static void testingProgram() {
        Label WarnerMusicGroup = new Label("Warner Music Group", "New York, USA");
        Author Gorillaz = new Author(WarnerMusicGroup, "Gorillaz", "Alternative rock");
        MusicTrack Pacman = new MusicTrack(Gorillaz, "Pac-Man", 14, 61);
        Pacman.getDuration();
    }

    public static void main( String args[] ) {
        testingProgram();
    }
}