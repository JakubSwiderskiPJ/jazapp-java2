package pl.edu.pjwstk.jaz.Models;

/**
 * Class Section
 */
public class Section {
    private int id;
    private String sectionName;

    public Section(int id, String sectionName, float price) {
        this.id = id;
        this.sectionName = sectionName
    }

    public int getId() {
        return id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}