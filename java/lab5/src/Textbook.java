class Textbook extends Publication {
    private final String subject;

    public Textbook(String title, String subject) {
        super(title); // добавляем себя в список
        this.subject = subject;
    }

    @Override
    void show() {
        System.out.println("Textbook Title: " + title + ", Subject: " + subject);
    }
}