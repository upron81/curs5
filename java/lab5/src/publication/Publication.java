package publication;



public abstract class Publication {
    protected String title;
    protected static Publication head;
    protected Publication next;

    public Publication(String title) {
        this.title = title;
        this.next = head;
        head = this;
    }

    public static void showList() {
        Publication current = head;
        while (current != null) {
            current.show();
            current = current.next;
        }
    }

    public abstract void show();
}
