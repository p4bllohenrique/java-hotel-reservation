package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    public Reservation() {
    }

    public Reservation(Integer roomNumber, Date chekcin, Date checkout) {
        this.roomNumber = roomNumber;
        this.checkin = chekcin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckin() {
        return this.checkin;
    }

    public Date getCheckout() {
        return this.checkout;
    }

    public long duration() {
        long diff = getCheckout().getTime() - getCheckin().getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String updateDates(Date chekcin, Date checkout) {
        Date now = new Date();
        if (checkin.before(now) || checkout.before(now)) {
            return "Reservation dates for update must be future date.";
        }
        if (!checkout.after(checkin)) {
            return "Check-out date must be after check-in date.";
        }

        this.checkin = chekcin;
        this.checkout = checkout;
        return null;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkin)
                + ", check-out: "
                + sdf.format(checkout)
                + ", "
                + duration()
                + " nights.";
    }
}
