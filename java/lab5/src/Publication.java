abstract class Publication {
    protected static Publication head; // начало связанного списка
    protected String title;
    protected Publication next; // ссылка на следующий элемент

    public Publication(String title) {
        this.title = title;
        this.next = head; // добавляем себя в начало списка
        head = this; // обновляем голову списка
    }

    public static void showList() {
        Publication current = head;
        while (current != null) {
            current.show();
            current = current.next;
        }
    }

    abstract void show(); // абстрактный метод для отображения информации
}