package Components;

import Resources.Resource;

public abstract class AbstractComponent implements Comparable {

    protected int ID;
    protected int price;
    protected int performance;
    protected String name;
    protected int quantity;
    protected Resource resource;
    protected String description;
    protected String type;
    protected String[] caratteristiche;

    @Deprecated
    public AbstractComponent() {
        /**
         Questo costruttore di default serve solo per le classi non ancora ultimate (altrimenti il programma non compila)
         rimuvovere quando ogni classe in Remove ha un suo costruttore
         */
    }

    public AbstractComponent(String[] input) {
        this.price = Integer.parseInt(input[4]);
        this.performance = Integer.parseInt(input[5]);
        this.quantity = Integer.parseInt(input[3]);
        this.ID = Integer.parseInt(input[0]);
        this.type = input[1].toUpperCase();
        this.caratteristiche = input[2].split("_");
        this.name = caratteristiche[0];
    }


    public int getPerformance() {
        return performance;
    }

    public int getID() {
        return ID;
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

    public String getType() {
        return type;
    }


    public String getName() {
        return name;
    }

    public String getSocket() {
        return resource.getTypeSocket();
    }

    public String getTypeRAM() {
        return resource.getTypeRAM();
    }

    public int getBit() {
        return resource.getnBit();
    }

    public String getCaseDim() {
        return resource.getDimensionCase();
    }

    @Override
    public String toString() {
        return "{" +
                "ID='" + ID + '\'' +
                ", price=" + price +
                ", performance=" + performance +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
