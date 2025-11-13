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

        /*This solution is less bad than the previous one, since the reservation validation logic is now delegated
        to the Reservation class itself rather than the main system. However, this solution still has a small validation
        issue during the object's initialization, because there is a validation that should be handled by the constructor —
        but since constructors cannot return a String, this solution will remain as it is for now. */

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

            String error = reservation.updateDates(checkin, checkout);
            if (error != null) {
                System.out.println("Error in reservation: " + error);
            } else {
                System.out.println("Reservation: " + reservation);
            }
        }
        sc.close();
    }
}