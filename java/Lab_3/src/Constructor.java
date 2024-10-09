import java.io.Serializable;

class Constructor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;

    public Constructor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}