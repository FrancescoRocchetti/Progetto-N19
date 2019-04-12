package Components;

import Resources.Resource;

public abstract class AbstractComponent implements Comparable {

    String ID;
    protected int price;
    protected int performance;
    protected String name;
    protected int quantity;
    protected Resource resource;
    protected String description;
    protected String[] caratteristiche;

    public AbstractComponent() {
        /**
            Questo costruttore di default serve solo per le classi non ancora ultimate (altrimenti il programma non compila)
            rimuvovere quando ogni classe in Components ha un suo costruttore
        */
    }

    public AbstractComponent(String[] input) {
        this.price=Integer.parseInt(input[4]);
        this.performance=Integer.parseInt(input[5]);
        this.quantity=Integer.parseInt(input[3]);
        this.ID=input[0];
        this.caratteristiche = input[2].split("_");
        this.name = caratteristiche[0];
    }


    public int getPerformance() {
        return performance;
    }

    public int getPrice() {
        return price;
    }

    public Resource getResource() {
        return resource;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int compareTo(Object o) {
        AbstractComponent c = (AbstractComponent) o;
        return this.performance - c.getPerformance();
    }

    @Override
    public String toString() {
        return "AbstractComponent{" +
                "ID='" + ID + '\'' +
                ", price=" + price +
                ", performance=" + performance +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
