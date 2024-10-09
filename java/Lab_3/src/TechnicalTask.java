import java.io.Serializable;

class TechnicalTask implements Serializable {
    private static final long serialVersionUID = 1L;
    private String description;
    private int numberOfFloors;

    public TechnicalTask(String description, int numberOfFloors) {
        this.description = description;
        this.numberOfFloors = numberOfFloors;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    @Override
    public String toString() {
        return "Техническое задание: " + description + ", Количество этажей: " + numberOfFloors;
    }
}