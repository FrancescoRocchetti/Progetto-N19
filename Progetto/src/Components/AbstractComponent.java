package Components;

public abstract class AbstractComponent implements Comparable {

    protected String ID;
    protected int price;
    protected int performance;
    protected String brand;
    protected String name;
    protected int quantity;


    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "AbstractComponent{" +
                "ID='" + ID + '\'' +
                ", price=" + price +
                ", performance=" + performance +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
