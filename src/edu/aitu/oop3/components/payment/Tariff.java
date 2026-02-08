package edu.aitu.oop3.components.payment;

public class Tariff {
    private final int id;
    private final String name;
   private final int cost;

   public Tariff(int id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
   }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}

