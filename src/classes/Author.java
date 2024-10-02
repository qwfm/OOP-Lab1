package classes;

public class Author {
    private String pseudonym;
    private String genre;
    private Label label;

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
