package model;

import dao.GuestDAO;
import entity.Guest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class GuestModel {
    GuestDAO guestDAO = new GuestDAO();

    public void addGuest(Guest guest) throws IOException {
        try {
            guestDAO.add(guest);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Guest getGuestBy(String email, String password) throws IOException {
        try {
            List<Guest> list = guestDAO.getAll();
            for (Guest item : list) {
                if (item.getEmail().equals(email) && item.getPassword().equals(password)) {
                    return item;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
