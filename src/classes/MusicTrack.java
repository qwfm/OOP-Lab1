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
            float totalSeconds = minutes * 60 + seconds;

            float newTotalSeconds = this.hours * 3600 + this.minutes * 60 + this.seconds + totalSeconds;

            this.hours = (int) (newTotalSeconds / 3600);
            this.minutes = (int) ((newTotalSeconds % 3600) / 60);
            this.seconds = newTotalSeconds % 60;
        }
    }

    public void getDuration() {
        if (hours>0) System.out.printf("%d:", (int) hours);
        else System.out.printf("%02d:%02d", (int) minutes, (int) seconds);
    }

    public float getHours() {
        return hours;
    }

    public float getMinutes() {
        return minutes;
    }

    public float getSeconds() {
        return seconds;
    }
}
