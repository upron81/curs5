import java.util.ArrayList;
import java.util.List;

public class SSSR {

    // Внутренний класс для хранения информации об изменении территориального деления
    public class TerritorialChange {
        private String date;         // Дата изменения
        private String description;  // Описание изменения
        private String region;       // Область или республика

        public TerritorialChange(String date, String description, String region) {
            this.date = date;
            this.description = description;
            this.region = region;
        }

        @Override
        public String toString() {
            return "Дата: " + date + ", Регион: " + region + ", Описание: " + description;
        }
    }

    // Список для хранения всех изменений
    private List<TerritorialChange> changesHistory;

    public SSSR() {
        this.changesHistory = new ArrayList<>();
    }

    // Метод для добавления нового изменения
    public void addChange(String date, String description, String region) {
        TerritorialChange change = new TerritorialChange(date, description, region);
        changesHistory.add(change);
    }

    // Метод для вывода всех изменений
    public void showHistory() {
        if (changesHistory.isEmpty()) {
            System.out.println("История изменений отсутствует.");
        } else {
            for (TerritorialChange change : changesHistory) {
                System.out.println(change);
            }
        }
    }
}
