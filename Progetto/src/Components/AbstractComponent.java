package Components;

import Resources.Resource;

public abstract class AbstractComponent implements Comparable {

    protected String ID;
    protected int price;
    protected int performance;
    protected String brand; // togliere
    protected String name;
    protected int quantity;
    protected Resource resource;


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
