public class Subscriber {
    private String name;
    private String phoneNumber;
    private int tariffId; // Ссылка на тариф по ID

    public Subscriber(String name, String phoneNumber, int tariffId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.tariffId = tariffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    @Override
    public String toString() {
        return "Абонент [имя='" + name + "', номер телефона='" + phoneNumber + "', тариф ID=" + tariffId + "]";
    }
}
