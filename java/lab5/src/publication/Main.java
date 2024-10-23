package publication;

import publication.items.Book;
import publication.items.Textbook;
import publication.items.Journal;

public class Main {
    public static void main(String[] args) {
        new Book("Effective Java", "Joshua Bloch");
        new Textbook("Introduction to Algorithms", "Computer Science");
        new Journal("Nature", "Vol 34");
        Publication.showList();
    }
}
