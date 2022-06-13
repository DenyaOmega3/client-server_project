package commands;

import dao.GuestDAO;
import entity.Guest;
import model.GuestModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (!isMailValid(email)) {
            return "/signup?not_valid=true";
        }

        //check if mail is valid
        Guest newGuest = new Guest(firstName,lastName,LocalDate.parse(birthDate),email,password);
        GuestDAO guestDAO = new GuestDAO();

        try {
            Guest checkIfExists = guestDAO.getByEMail(email);
            if (checkIfExists != null) {
                return "/signup?account_exists=true";
            }
            guestDAO.add(newGuest);
            HttpSession session = request.getSession();
            session.setAttribute("user", newGuest);
            session.setMaxInactiveInterval(5*60*60); //one hour

            System.out.println(isMailValid(email));

            return "/mainpage";
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isMailValid(String input) {
        String emailReget = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailReget,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(input);
        return matcher.find();
    }
}
