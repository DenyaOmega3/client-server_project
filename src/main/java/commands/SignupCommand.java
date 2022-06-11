package commands;

import entity.Guest;
import model.GuestModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class SignupCommand implements ICommand {
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String BIRTH_DATE = "birthDate";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String birthDate = request.getParameter(BIRTH_DATE);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        Guest guest = new Guest(firstName,lastName,LocalDate.parse(birthDate),email,password);

        GuestModel guestModel = new GuestModel();
        guestModel.addGuest(guest);

        HttpSession session = request.getSession();
        session.setAttribute("user", guest);
        session.setMaxInactiveInterval(5*60);

        return "/mainpage";
    }
}
