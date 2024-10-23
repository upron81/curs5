class Journal extends Publication {
    private final String issue;

    public Journal(String title, String issue) {
        super(title); // добавляем себя в список
        this.issue = issue;
    }

    @Override
    void show() {
        System.out.println("Journal Title: " + title + ", Issue: " + issue);
    }
}