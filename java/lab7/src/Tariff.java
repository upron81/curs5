public class Tariff {
    private static int idCounter = 0; // Счетчик для создания уникальных идентификаторов
    private int id; // Уникальный идентификатор тарифа
    private String name;
    private double cost;

    public Tariff(String name, double cost) {
        this.id = ++idCounter;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Тариф [id=" + id + ", название='" + name + "', стоимость=" + cost + "]";
    }
}
