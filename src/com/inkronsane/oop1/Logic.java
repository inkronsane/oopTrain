package com.inkronsane.oop1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Logic {
  private final List<Train> trains;

  public Logic() {
    trains = new ArrayList<>();
    trains.add(new Train("Ужгород", 101, "08:00", 200, 110, 20));
    trains.add(new Train("Львів", 102, "09:30", 220, 45, 20));
    trains.add(new Train("Одеса", 103, "10:45", 320, 55, 20));
    trains.add(new Train("Амстердам", 104, "12:15", 290, 47, 20));
    trains.add(new Train("Мукачево", 105, "14:30", 310, 52, 158));
    trains.add(new Train("Черкаси", 106, "16:45", 275, 44, 131));
    trains.add(new Train("Івано-Франківськ", 107, "18:00", 330, 57, 173));
    trains.add(new Train("Запоріжжя", 108, "19:30", 305, 51, 154));
    trains.add(new Train("Будапешт", 109, "21:15", 285, 46, 139));
    trains.add(new Train("Полтава", 110, "22:45", 315, 53, 161));
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);
    int choice;
    do {
      System.out.println("Меню:");
      System.out.println("1. Список поїздів до пункту призначення");
      System.out.println("2. Список поїздів до пункту призначення після певного часу");
      System.out.println("3. Список поїздів до пункту призначення з доступними загальними місцями");
      System.out.println("4. Купити білет на потяг");
      System.out.println("0. Вийти");
      System.out.print("Виберіть опцію: ");
      choice = scanner.nextInt();
      scanner.nextLine();
      switch (choice) {
        case 1:
          System.out.print("Введіть пункт призначення: ");
          String destination = scanner.nextLine();
          trainsToDestination(destination);
          break;
        case 2:
          System.out.print("Введіть пункт призначення: ");
          String destination2 = scanner.nextLine();
          System.out.print("Введіть час: ");
          String time = scanner.nextLine();
          trainsToDestinationAfterTime(destination2, time);
          break;
        case 3:
          System.out.print("Введіть пункт призначення: ");
          String destination3 = scanner.nextLine();

          List<Train> trainsWithAvailableSeats = getTrainsWithAvailableSeats(destination3);

          if (!trainsWithAvailableSeats.isEmpty()) {
            System.out.println("Список поїздів до " + destination3 + " з доступними загальними місцями:");
            for (Train train : trainsWithAvailableSeats) {
              System.out.println(train);
            }
          } else {
            System.out.println("На жаль, немає потягів до " + destination3 + " з доступними загальними місцями.");
          }
          break;

        case 4:
          System.out.print("Введіть номер потяга: ");
          int trainNumberToBook = scanner.nextInt();
          System.out.print("Введіть кількість броньованих купейних місць: ");
          int numCoupeSeatsToBook = scanner.nextInt();
          System.out.print("Введіть кількість броньованих плацкартних місць: ");
          int numPlatzkartSeatsToBook = scanner.nextInt();

          boolean bookingResult = bookSeats(trainNumberToBook, numCoupeSeatsToBook, numPlatzkartSeatsToBook);

          String bookingMessage = bookingResult ? "Місця успішно заброньовані!" : "Помилка під час бронювання місць.";
          System.out.println(bookingMessage);
          break;
        case 0:
          break;
        default:
          System.out.println("Неправильний вибір. Спробуйте ще раз.");
      }
    } while (choice != 0);
  }

  public void trainsToDestination(String destination) {
    System.out.println("Список поїздів до " + destination + ":");

    boolean foundTrain = false;

    for (Train train : trains) {
      if (train.getDestination().equals(destination)) {
        System.out.println(train);
        foundTrain = true;
      }
    }

    if (!foundTrain) {
      System.out.println("На жаль, немає потягів до " + destination);
    }
  }

  public void trainsToDestinationAfterTime(String destination, String departureTime) {
    System.out.println(
        "Список поїздів до " + destination + ", які відправляються після " + departureTime + ":");
    boolean[] foundTrain = {false};
    trains.stream()
        .filter(train -> train.getDestination().equals(destination) && isAfterDepartureTime(train, departureTime))
        .forEach(train -> {
          System.out.println(train);
          foundTrain[0] = true;
        });
    if (!foundTrain[0]) {
      System.out.println("На жаль, немає потягів до " + destination + " після " + departureTime);
    }
  }

  public List<Train> getTrainsWithAvailableSeats(String destination) {
    List<Train> trainsToDestination = new ArrayList<>();

    for (Train train : trains) {
      if (train.getDestination().equalsIgnoreCase(destination) && train.getAvailablePlatzkartSeats() > 0) {
        trainsToDestination.add(train);
      }
    }

    return trainsToDestination;
  }

  public boolean bookSeats(int trainNumber, int numCoupeSeats, int numPlatzkartSeats) {
    Train trainToBook = findTrainByNumber(trainNumber);
    if (trainToBook == null) {
      System.out.println("Потяг з номером " + trainNumber + " не знайдений.");
      return false;
    }
    trainToBook.reserveSeats(numCoupeSeats, numPlatzkartSeats);
    return true;
  }

  private Train findTrainByNumber(int trainNumber) {
    for (Train train : trains) {
      if (train.getTrainNumber() == trainNumber) {
        return train;
      }
    }
    return null;
  }

  private boolean isAfterDepartureTime(Train train, String departureTime) {
    String trainDepartureTime = train.getDepartureTime();
    return trainDepartureTime.compareTo(departureTime) > 0;
  }
}
