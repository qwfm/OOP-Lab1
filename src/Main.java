import classes.*;

import java.util.ArrayList;
import java.util.List;


public class Main {

    static void testingProgram() {
        Genre g1 = new Genre("Alternative rock");
        Genre g2 = new Genre("Nu-metal");
        Genre g3 = new Genre("Jazz");

        Label l1 = new Label("Warner Music Group", "New York, USA");

        Author a1 = new Author(l1, "Gorillaz", g1);
        Author a2 = new Author(l1, "Linkin Park", g2);
        Author a3 = new Author(l1, "Frank Sinatra", g3);

        Song m4 = new Song(a1, "Pac-Man", 3, 51);
        Song m7 = new Song(a1, "Clint Eastwood", 2, 25);
        Song m3 = new Song(a2, "Numb", 3, 8);
        Song m1 = new Song(a2, "In the end", 3, 39);
        Song m5 = new Song(a2, "Faint", 2, 49);
        Song m6 = new Song(a2, "In the end", 3, 39);
        Song m2 = new Song(a3, "My way", 3, 53);

        List<Song> testList = new ArrayList<>();
        testList.add(m1); testList.add(m2); testList.add(m3); testList.add(m4);
        testList.add(m5); testList.add(m6); testList.add(m7);

        SongCollection test_Collection = new SongCollection("test", testList);
        test_Collection.sortSongsByGenre();
        test_Collection.print();
    }

    public static void main( String args[] ) {
        testingProgram();
    }
}