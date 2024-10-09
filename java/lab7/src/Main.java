import java.util.Scanner;

public class Main {
    private static SubscriberManager subscriberManager;
    private static TariffManager tariffManager;
    private static final String SUBSCRIBERS_FILE = "subscribers.xml";
    private static final String TARIFFS_FILE = "tariffs.xml";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tariffManager = new TariffManager();
        subscriberManager = new SubscriberManager(tariffManager);

        try {
            // Попробуем загрузить абонентов из файла
            subscriberManager.getSubscribers().addAll(XMLHandler.loadFromXML(SUBSCRIBERS_FILE));
            System.out.println("Данные абонентов успешно загружены из файла.");
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке абонентов: " + e.getMessage());
        }

        int choice;
        do {
            System.out.println("\nМеню:");
            System.out.println("1. Управление абонентами");
            System.out.println("2. Управление тарифами");
            System.out.println("0. Выйти и сохранить");

            choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после ввода числа

            switch (choice) {
                case 1:
                    manageSubscribers(scanner);
                    break;
                case 2:
                    manageTariffs(scanner);
                    break;
                case 0:
                    try {
                        XMLHandler.saveToXML(subscriberManager.getSubscribers(), SUBSCRIBERS_FILE);
                        tariffManager.saveTariffs(); // Сохранить тарифы при выходе
                        System.out.println("Данные успешно сохранены.");
                    } catch (Exception e) {
                        System.out.println("Ошибка при сохранении данных: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        } while (choice != 0);
    }

    // Меню управления абонентами
    private static void manageSubscribers(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nМеню абонентов:");
            System.out.println("1. Добавить абонента");
            System.out.println("2. Показать всех абонентов");
            System.out.println("3. Обновить абонента");
            System.out.println("4. Удалить абонента");
            System.out.println("0. Вернуться в главное меню");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    subscriberManager.addSubscriber(scanner);
                    break;
                case 2:
                    subscriberManager.showAllSubscribers();
                    break;
                case 3:
                    subscriberManager.updateSubscriber(scanner);
                    break;
                case 4:
                    subscriberManager.deleteSubscriber(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        } while (choice != 0);
    }

    // Меню управления тарифами
    private static void manageTariffs(Scanner scanner) {
        int choice;
        do {
            System.out.println("\nМеню тарифов:");
            System.out.println("1. Добавить тариф");
            System.out.println("2. Показать все тарифы");
            System.out.println("3. Обновить тариф");
            System.out.println("4. Удалить тариф");
            System.out.println("0. Вернуться в главное меню");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    tariffManager.addTariff(scanner);
                    break;
                case 2:
                    tariffManager.showAllTariffs();
                    break;
                case 3:
                    tariffManager.updateTariff(scanner);
                    break;
                case 4:
                    tariffManager.deleteTariff(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        } while (choice != 0);
    }
}
