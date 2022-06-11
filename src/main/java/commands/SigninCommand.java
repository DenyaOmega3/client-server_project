package commands;

import dao.GuestDAO;
import entity.Guest;
import model.GuestModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SigninCommand implements ICommand {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("logging in");
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        PrintWriter pt = response.getWriter();

        GuestModel guestModel = new GuestModel();
        Guest guest = guestModel.getGuestBy(email, password);

        HttpSession session = request.getSession();
        session.setAttribute("user", guest);
        session.setMaxInactiveInterval(5*60);

        if (guest != null) {
            System.out.println("Success");
            System.out.println(guest);
            //session.setAttribute("user", guest.getFirstName());
            return "/mainpage";
        }
        return null;
    }
}
