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

  public Train(String destination, int trainNumber, String departureTime, int totalSeats, int coupeSeats, int platzkartSeats) {
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

  public int getTrainNumber() { return trainNumber; }

  public String getDepartureTime() {
    return departureTime;
  }

  public int getTotalSeats() {
    return totalSeats;
  }

  public int getCoupeSeats() {
    return coupeSeats;
  }

  public int getPlatzkartSeats() {
    return platzkartSeats;
  }

  public int getReservedCoupeSeats() {
    return reservedCoupeSeats;
  }

  public int getReservedPlatzkartSeats() {
    return reservedPlatzkartSeats;
  }

  public void reserveSeats(int numCoupeSeats, int numPlatzkartSeats) {
    int availableCoupeSeats = getCoupeSeats() - reservedCoupeSeats;
    int availablePlatzkartSeats = getPlatzkartSeats() - reservedPlatzkartSeats;

    boolean availableSeats = true;

    if (numCoupeSeats > availableCoupeSeats) {
      System.out.println("Недостатньо купейних місць для бронювання.");
      availableSeats = false;
    }

    if (numPlatzkartSeats > availablePlatzkartSeats) {
      System.out.println("Недостатньо плацкартних місць для бронювання.");
      availableSeats = false;
    }

    if (availableSeats) {
      reservedCoupeSeats += numCoupeSeats;
      reservedPlatzkartSeats += numPlatzkartSeats;
      System.out.println("Ваші місця успішно заброньовано!");
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
    return "Потяг номер " + trainNumber + " відправляється до міста " + destination + ", о " + departureTime;
  }
}
