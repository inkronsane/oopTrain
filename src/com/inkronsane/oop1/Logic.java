package com.inkronsane.oop1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Logic {

    private final List<Train> trains;

    public Logic() {
        trains = new ArrayList<>();

        trains.add(new Train("Ужгород", 101, "08:00", 200, 110, 29));
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
            System.out.println("3. Список поїздів до пункту призначення з вільними місцями");
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
                    getTrainsWithAvailableSeats(destination3);
                    break;
                case 4:
                    System.out.print("Введіть номер потяга: ");
                    int trainNumberToBook = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Оберіть тип місця: 1(Купейне) або 2(Плацкартне)");
                    int seatType = scanner.nextInt();
                    scanner.nextLine();
                    int numSeatsToBook;

                    if (seatType == 1 || seatType == 2) {
                        System.out.print("Введіть кількість броньованих місць: ");
                        numSeatsToBook = scanner.nextInt();
                    } else {
                        System.out.println("Неправильний вибір типу місця.");
                        break;
                    }

                    bookSeats(trainNumberToBook, numSeatsToBook, seatType);
                    break;

                case 0:
                    break;
                default:
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
            }
            System.out.println("Натисніть Enter для продовження...");
            scanner.nextLine();
        }
        while (choice != 0);
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
            "Список поїздів до " + destination + ", які відправляються після " + departureTime
                + ":");
        boolean foundTrain = false;
        for (Train train : trains) {
            if (train.getDestination().equals(destination) && isAfterDepartureTime(train,
                departureTime)) {

                System.out.println(train);
                foundTrain = true;
            }
        }
        if (!foundTrain) {
            System.out.println(
                "На жаль, немає потягів до " + destination + " після " + departureTime);
        }
    }

    public void getTrainsWithAvailableSeats(String destination) {
        for (Train train : trains) {
            if (train.getDestination().equalsIgnoreCase(destination)) {
                int availablePlatzkartSeats = train.getAvailablePlatzkartSeats();
                int availableCoupeSeats = train.getAvailableCoupeSeats();

                if (availablePlatzkartSeats > 0 || availableCoupeSeats > 0) {
                    System.out.println(
                        "Поїзд номер " + train.getTrainNumber() + " до " + destination + ":");
                    System.out.println("Доступно плацкартних місць: " + availablePlatzkartSeats);
                    System.out.println("Доступно купейних місць: " + availableCoupeSeats);
                } else {
                    System.out.println("Потяг немає вільних для бронювання місць!");
                }
            }
        }
    }

    public void bookSeats(int trainNumber, int numSeatsToBook, int seatType) {
        Train trainToBook = findTrainByNumber(trainNumber);
        if (trainToBook == null) {
            System.out.println("Потяг з номером " + trainNumber + " не знайдений.");
            return;
        }

        boolean bookingResult = false;

        switch (seatType) {
            case 1: // Купейне місце
                if (numSeatsToBook > 0) {
                    bookingResult = trainToBook.reserveSeats(numSeatsToBook, 0);
                }
                break;
            case 2: // Плацкартне місце
                if (numSeatsToBook > 0) {
                    bookingResult = trainToBook.reserveSeats(0, numSeatsToBook);
                }
                break;
            default:
                System.out.println("Неправильний вибір типу місця.");
                break;
        }

        if (bookingResult) {
            System.out.println("Місця успішно заброньовані!");
        } else {
            System.out.println("Недостатньо місць для бронювання.");
        }
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
