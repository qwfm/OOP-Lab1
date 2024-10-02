package classes;

public class Author {
    public String pseudonym;
    public String genre;
    public Label label;

    public Author() {}

    public Author(Label label, String pseudonym, String genre) {
        this.label = label;
        this.pseudonym = pseudonym;
        this.genre = genre;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
