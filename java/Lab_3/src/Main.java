import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bureau bureau = new Bureau();

        while (true) {
            System.out.println("1. Создать техническое задание");
            System.out.println("2. Показать проекты");
            System.out.println("3. Выйти");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createTechnicalTask(scanner, bureau);
                    break;
                case 2:
                    showProjects(bureau);
                    break;
                case 3:
                    System.out.println("Завершение программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private static void createTechnicalTask(Scanner scanner, Bureau bureau) {
        System.out.print("Введите имя заказчика: ");
        String clientName = scanner.nextLine();
        Client client = new Client(clientName);

        System.out.print("Введите описание технического задания: ");
        String taskDescription = scanner.nextLine();

        System.out.print("Введите количество этажей: ");
        int numberOfFloors = scanner.nextInt();

        TechnicalTask technicalTask = new TechnicalTask(taskDescription, numberOfFloors);
        Project project = bureau.createProject(client, technicalTask);

        System.out.println("Проект создан с расчетной стоимостью проектирования: " + project.getDesignCost() + " руб.");

        try {
            DataManager.saveData(bureau, "bureauData.dat");
            System.out.println("Данные сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    private static void showProjects(Bureau bureau) {
        try {
            Bureau loadedBureau = (Bureau) DataManager.loadData("bureauData.dat");
            System.out.println("Проекты:");

            for (Project project : loadedBureau.getProjects()) {
                System.out.println(project);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка загрузки данных: " + e.getMessage());
        }
    }
}