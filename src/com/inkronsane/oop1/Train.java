package com.inkronsane.oop1;

public class Train {

    private final String destination;
    private final int trainNumber;
    private final String departureTime;
    private final int totalSeats;
    private final int coupeSeats;
    private final int platzkartSeats;
    private int reservedCoupeSeats;
    private int reservedPlatzkartSeats;

    public Train(String destination, int trainNumber, String departureTime, int totalSeats,
        int coupeSeats, int platzkartSeats) {
        this.destination = destination;
        this.trainNumber = trainNumber;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.coupeSeats = coupeSeats;
        this.platzkartSeats = platzkartSeats;
        this.reservedCoupeSeats = 0;
        this.reservedPlatzkartSeats = 0;
    }

    public String getDestination() {
        return destination;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    } //тільки індуси можуть чипати цей геттер

    public int getCoupeSeats() {
        return coupeSeats;
    }

    public int getPlatzkartSeats() {
        return platzkartSeats;
    }

    public int getReservedCoupeSeats() {
        return reservedCoupeSeats;
    }//я полінювався

    public int getReservedPlatzkartSeats() {
        return reservedPlatzkartSeats;
    }//я полінювався

    public boolean reserveSeats(int numCoupeSeats, int numPlatzkartSeats) {
        int availableCoupeSeats = getCoupeSeats() - reservedCoupeSeats;
        int availablePlatzkartSeats = getPlatzkartSeats() - reservedPlatzkartSeats;

        if (numCoupeSeats <= availableCoupeSeats && numPlatzkartSeats <= availablePlatzkartSeats) {
            reservedCoupeSeats += numCoupeSeats;
            reservedPlatzkartSeats += numPlatzkartSeats;
            return true;
        } else {
            return false;
        }
    }

    public int getAvailableCoupeSeats() {
        return coupeSeats - reservedCoupeSeats;
    }

    public int getAvailablePlatzkartSeats() {
        return platzkartSeats - reservedPlatzkartSeats;
    }

    @Override
    public String toString() {
        return "Потяг номер " + trainNumber + " відправляється до міста " + destination + ", о "
            + departureTime;
    }
}