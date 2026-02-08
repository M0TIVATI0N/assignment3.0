package edu.aitu.oop3.components.payment;

public class Invoice {
    private String plateNumber;
    private double totalAmount;
    private int durationMinutes;
    private String spotType;

    private Invoice() {}

    public static class Builder {
        private final Invoice invoice = new Invoice();

        public Builder setPlate(String plate) {
            invoice.plateNumber = plate;
            return this;
        }

        public Builder setAmount(double amount) {
            invoice.totalAmount = amount;
            return this;
        }

        public Builder setDuration(int minutes) {
            invoice.durationMinutes = minutes;
            return this;
        }

        public Builder setSpotType(String type) {
            invoice.spotType = type;
            return this;
        }

        public Invoice build() {
            return invoice;
        }
    }

    public String getPlateNumber() { return plateNumber; }
    public double getTotalAmount() { return totalAmount; }
    public int getDurationMinutes() { return durationMinutes; }
    public String getSpotType() { return spotType; }
}