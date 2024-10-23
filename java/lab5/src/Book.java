class Book extends Publication {
    private final String author;

    public Book(String title, String author) {
        super(title);
        this.author = author;
    }

    @Override
    void show() {
        System.out.println("Book Title: " + title + ", Author: " + author);
    }
}