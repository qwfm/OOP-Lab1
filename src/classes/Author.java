package classes;

public class Author {
    private int authorID;
    private String name;
    private Genre genre;
    private Label label;

    public Author() {}

    public Author(Label label, String name, Genre genre) {
        this.label = label;
        this.name = name;
        this.genre = genre;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public Label getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
