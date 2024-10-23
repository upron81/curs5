package publication.items;

import publication.Publication;

public class Textbook extends Publication {
    private String subject;

    public Textbook(String title, String subject) {
        super(title);
        this.subject = subject;
    }

    @Override
    public void show() {
        System.out.println("Textbook Title: " + title + ", Subject: " + subject);
    }
}
