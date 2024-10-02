package classes;

public class MusicTrack {
    private Author author;
    private String name;
    private float hours = 0;
    private float minutes = 0;
    private float seconds = 0;

    public MusicTrack() {}

    public MusicTrack(Author author, String name, float minutes, float seconds) {
        this.author = author;
        this.name = name;
        setDuration(minutes, seconds);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(float minutes, float seconds) {
        if (seconds < 0 || minutes < 0) {
            System.out.print("Time input is wrong - try again");
        } else {
            this.minutes += (int) (seconds / 60);
            this.seconds = seconds % 60;

            this.hours += (int) (minutes / 60);
            this.minutes += minutes % 60;

            if (this.minutes >= 60) {
                this.hours += (int) (this.minutes / 60);
                this.minutes = this.minutes % 60;
            }
        }
    }

    public void getDuration() {
        if (hours>0) System.out.printf("%d:", (int) hours);
        System.out.printf("%02d:%02d", (int) minutes, (int) seconds);
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
