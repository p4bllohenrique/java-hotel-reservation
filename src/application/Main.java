package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static LocalDate readDate(Scanner sc, DateTimeFormatter dtf, String message) {
        while (true) {
            System.out.print(message);
            String text = sc.next();

            try {
                return LocalDate.parse(text, dtf);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a date in DD/MM/YYYY format.");
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            int roomNumber;
            while (true) {
                System.out.print("Room number: ");
                if (sc.hasNextInt()) {
                    roomNumber = sc.nextInt();
                    break;
                } else {
                    System.out.println("Invalid number. Please enter an integer.");
                    sc.next();
                }
            }

            sc.nextLine();
            LocalDate checkin = readDate(sc, dtf, "Check-in date (DD/MM/YYYY): ");
            LocalDate checkout = readDate(sc, dtf, "Check-out date (DD/MM/YYYY): ");

            Reservation reservation = new Reservation(roomNumber, checkin, checkout);
            System.out.println("Reservation: " + reservation);

            System.out.println();
            System.out.println("Enter new dates to update the reservation: ");
            checkin = readDate(sc, dtf, "Check-in date (DD/MM/YYYY): ");
            checkout = readDate(sc, dtf, "Check-out date (DD/MM/YYYY): ");

            reservation.updateDates(checkin, checkout);
            System.out.println("Reservation: " + reservation);
        } catch (DomainException e) {
            System.out.println("Error in reservation: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Unexpected error");
        }
        sc.close();
    }
}