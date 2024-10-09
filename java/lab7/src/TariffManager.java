import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TariffManager {
    private List<Tariff> tariffs;
    private static final String XML_FILE = "tariffs.xml";

    public TariffManager() {
        this.tariffs = new ArrayList<>();
        loadTariffs();
    }

    // Загрузить тарифы из XML
    public void loadTariffs() {
        try {
            tariffs = XMLHandler.loadTariffsFromXML(XML_FILE);
            System.out.println("Тарифы успешно загружены из файла.");
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке тарифов: " + e.getMessage());
        }
    }

    // Сохранить тарифы в XML
    public void saveTariffs() {
        try {
            XMLHandler.saveTariffsToXML(tariffs, XML_FILE);
            System.out.println("Тарифы успешно сохранены в файл.");
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении тарифов: " + e.getMessage());
        }
    }

    // Добавить тариф
    public void addTariff(Scanner scanner) {
        System.out.print("Введите название тарифа: ");
        String name = scanner.nextLine();
        System.out.print("Введите стоимость тарифа: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();
        tariffs.add(new Tariff(name, cost));
        System.out.println("Тариф добавлен.");
    }

    // Показать все тарифы
    public void showAllTariffs() {
        if (tariffs.isEmpty()) {
            System.out.println("Список тарифов пуст.");
        } else {
            for (Tariff tariff : tariffs) {
                System.out.println(tariff);
            }
        }
    }

    // Обновить тариф
    public void updateTariff(Scanner scanner) {
        showAllTariffs();
        System.out.print("Выберите тариф для обновления по ID: ");
        int tariffId = scanner.nextInt();
        scanner.nextLine();

        Tariff tariff = getTariffById(tariffId);
        if (tariff != null) {
            System.out.print("Новое название (оставьте пустым, чтобы не изменять): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                tariff.setName(newName);
            }

            System.out.print("Новая стоимость (оставьте пустым, чтобы не изменять): ");
            String newCost = scanner.nextLine();
            if (!newCost.isEmpty()) {
                tariff.setCost(Double.parseDouble(newCost));
            }

            System.out.println("Тариф обновлен.");
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    // Удалить тариф
    public void deleteTariff(Scanner scanner) {
        showAllTariffs();
        System.out.print("Выберите тариф для удаления по ID: ");
        int tariffId = scanner.nextInt();
        scanner.nextLine();

        Tariff tariff = getTariffById(tariffId);
        if (tariff != null) {
            tariffs.remove(tariff);
            System.out.println("Тариф удален.");
        } else {
            System.out.println("Неверный выбор.");
        }
    }

    // Получить тариф по ID
    public Tariff getTariffById(int id) {
        for (Tariff tariff : tariffs) {
            if (tariff.getId() == id) {
                return tariff;
            }
        }
        return null; // Тариф не найден
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }
}
