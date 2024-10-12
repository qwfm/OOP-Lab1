package classes;

public class Label {
    private int labelID;
    private String name;
    private String location;

    public Label() {}

    public Label(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLabelID() {
        return labelID;
    }

    public void setLabelID(int labelID) {
        this.labelID = labelID;
    }
}
