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

        //GuestModel guestModel = new GuestModel();
        GuestDAO guestDAO = new GuestDAO();

        //Guest guest = guestModel.getGuestBy(email, password);
        //TODO: get information by email and password
        try {
            Guest guest = guestDAO.getByEMail(email);
            if (guest == null) {
                return "/signin?user_not_found=true";
            }
            if (!guest.getPassword().equals(password)) {
                return "/signin?wrong_password=true";
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", guest);
            session.setMaxInactiveInterval(5*60);
            return "/mainpage";
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
