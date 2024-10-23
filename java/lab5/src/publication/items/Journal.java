package publication.items;

import publication.Publication;

public class Journal extends Publication {
    private String issue;

    public Journal(String title, String issue) {
        super(title);
        this.issue = issue;
    }

    @Override
    public void show() {
        System.out.println("Journal Title: " + title + ", Issue: " + issue);
    }
}
