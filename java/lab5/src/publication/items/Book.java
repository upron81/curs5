package publication.items;

import publication.Publication;

public class Book extends Publication {
    private String author;

    public Book(String title, String author) {
        super(title);
        this.author = author;
    }

    @Override
    public void show() {
        System.out.println("Book Title: " + title + ", Author: " + author);
    }
}
