import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int COST_PER_FLOOR = 1000;
    private Client client;
    private TechnicalTask technicalTask;
    private int designCost;
    private List<Constructor> constructors;

    public Project(Client client, TechnicalTask technicalTask) {
        this.client = client;
        this.technicalTask = technicalTask;
        this.designCost = calculateDesignCost();
        this.constructors = new ArrayList<>();
        createConstructorTeam();
    }

    public int getDesignCost() {
        return designCost;
    }

    private int calculateDesignCost() {
        return technicalTask.getNumberOfFloors() * COST_PER_FLOOR;
    }

    private void createConstructorTeam() {
        constructors.add(new Constructor("Инженер Петров"));
        constructors.add(new Constructor("Архитектор Иванов"));
    }

    @Override
    public String toString() {
        return "Проект для клиента: " + client + ", " + technicalTask + ", Стоимость проектирования: " + designCost + " руб.";
    }
}