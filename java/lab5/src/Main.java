public class Main {
    public static void main(String[] args) {
        new Book("Effective Java", "Joshua Bloch");
        new Textbook("Introduction to Algorithms", "Computer Science");
        new Journal("Nature", "Vol 34");

        // Просмотр списка
        Publication.showList();
    }
}
