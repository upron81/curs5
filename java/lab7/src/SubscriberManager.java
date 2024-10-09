import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubscriberManager {
    private List<Subscriber> subscribers;
    private TariffManager tariffManager;

    public SubscriberManager(TariffManager tariffManager) {
        this.subscribers = new ArrayList<>();
        this.tariffManager = tariffManager;
    }

    // Добавить абонента
    public void addSubscriber(Scanner scanner) {
        System.out.print("Введите имя абонента: ");
        String name = scanner.nextLine();
        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();

        tariffManager.showAllTariffs();
        System.out.print("Выберите тариф по ID: ");
        int tariffId = scanner.nextInt();
        scanner.nextLine();

        if (tariffManager.getTariffById(tariffId) != null) {
            subscribers.add(new Subscriber(name, phoneNumber, tariffId));
            System.out.println("Абонент добавлен.");
        } else {
            System.out.println("Неверный выбор тарифа.");
        }
    }

    // Показать всех абонентов
    public void showAllSubscribers() {
        if (subscribers.isEmpty()) {
            System.out.println("Список абонентов пуст.");
        } else {
            for (Subscriber subscriber : subscribers) {
                Tariff tariff = tariffManager.getTariffById(subscriber.getTariffId());
                System.out.println(subscriber + " (Тариф: " + (tariff != null ? tariff.getName() : "не найден") + ")");
            }
        }
    }

    // Обновить абонента
    public void updateSubscriber(Scanner scanner) {
        showAllSubscribers();
        System.out.print("Выберите абонента для обновления по номеру: ");
        int subscriberIndex = scanner.nextInt();
        scanner.nextLine();

        if (subscriberIndex >= 1 && subscriberIndex <= subscribers.size()) {
            Subscriber subscriber = subscribers.get(subscriberIndex - 1);
            System.out.print("Новое имя (оставьте пустым, чтобы не изменять): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                subscriber.setName(newName);
            }

            System.out.print("Новый номер телефона (оставьте пустым, чтобы не изменять): ");
            String newPhoneNumber = scanner.nextLine();
            if (!newPhoneNumber.isEmpty()) {
                subscriber.setPhoneNumber(newPhoneNumber);
            }

            tariffManager.showAllTariffs();
            System.out.print("Выберите новый тариф по ID (оставьте 0, чтобы не изменять): ");
            int tariffId = scanner.nextInt();
            scanner.nextLine();

            if (tariffId > 0 && tariffManager.getTariffById(tariffId) != null) {
                subscriber.setTariffId(tariffId);
            }

            System.out.println("Абонент обновлен.");
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    // Удалить абонента
    public void deleteSubscriber(Scanner scanner) {
        showAllSubscribers();
        System.out.print("Выберите абонента для удаления по номеру: ");
        int subscriberIndex = scanner.nextInt();
        scanner.nextLine();

        if (subscriberIndex >= 1 && subscriberIndex <= subscribers.size()) {
            subscribers.remove(subscriberIndex - 1);
            System.out.println("Абонент удален.");
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }
}
