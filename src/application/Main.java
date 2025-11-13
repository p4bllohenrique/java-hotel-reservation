package application;

import model.entities.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Room number: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("Check-in date (DD/MM/YYYY): ");
        Date checkin = sdf.parse(sc.next());
        System.out.print("Check-out date (DD/MM/YYYY)");
        Date checkout = sdf.parse(sc.next());

        /*This logic is very poor considering that the validation of the criteria
        for the reservation is being done in the main program (Main). */

        if (!checkout.after(checkin)) {
            System.out.println("Erro in reservation: Check-out date must be after check-in date.");
        } else {
            Reservation reservation = new Reservation(roomNumber, checkin, checkout);
            System.out.println("Reservation: " + reservation);

            System.out.println();
            System.out.println("Enter date to update the reservation:");
            System.out.print("Check-in date (DD/MM/YYYY): ");
            checkin = sdf.parse(sc.next());
            System.out.print("Check-out date (DD/MM/YYYY)");
            checkout = sdf.parse(sc.next());

            reservation.updateDates(checkin, checkout);
            System.out.println("Reservation: " + reservation);

            Date now = new Date();
            if (checkin.before(now) || checkout.before(now)) {
                System.out.println("Error in reservation: Reservation dates for update must be future date.");
            } else if (!checkout.after(checkin)) {
                System.out.println("Erro in reservation: Check-out date must be after check-in date.");
            } else {
                reservation.updateDates(checkin, checkout);
                System.out.println("Reseervation: " + reservation);
            }
        }
        sc.close();
    }
}