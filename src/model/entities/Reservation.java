package model.entities;

import model.exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reservation {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private Integer roomNumber;
    private LocalDate checkin;
    private LocalDate checkout;

    public Reservation() {
    }

    public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) throws DomainException {
        LocalDate now = LocalDate.now();
        if (checkin.isBefore(now) || checkout.isBefore(now))
            throw new DomainException("You cannot make a reservation for a past date.");
        if (!checkout.isAfter(checkin)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return this.roomNumber;
    }

    public LocalDate getCheckin() {
        return this.checkin;
    }

    public LocalDate getCheckout() {
        return this.checkout;
    }

    public long duration() {
        return ChronoUnit.DAYS.between(getCheckin(), getCheckout());
    }

    public void updateDates(LocalDate checkin, LocalDate checkout) throws DomainException {
        LocalDate now = LocalDate.now();
        if (checkin.isBefore(now) || checkout.isBefore(now)) {
            throw new DomainException("Reservation dates must be future dates.");
        }
        if (!checkout.isAfter(checkin)) {
            throw new DomainException( "Check-out date must be after check-in date.");
        }
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + dtf.format(checkin)
                + ", check-out: "
                + dtf.format(checkout)
                + ", "
                + duration()
                + " nights.";
    }
}
