package pl.edu.pjwstk.jaz.Models;

/**
 *
 */
public class Auction {
    protected int id;
    protected String title;
    protected String description;
    protected String author;
    protected float price;

    public Auction() {
    }

    public Auction(int id) {
        this.id = id;
    }

    public Auction(int id, String title, String description, float price) {
        this(title, description, price);
        this.id = id;
    }

    public Auction(String title, String description, float price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return author;
    }
    Ž
    public void setDescription(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}