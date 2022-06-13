package servlet;

import commands.ICommand;
import dao.GuestDAO;
import entity.Film;
import entity.Guest;
import model.FilmModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "BaseServlet", value = "/BaseServlet")
public class BaseServlet extends HttpServlet {
    ControllerHelper controllerHelper = ControllerHelper.getInstance();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        try {
            if (request.getParameter("command").equals("init")) {
                List<Film> listFilms = listFilms();
                request.setAttribute("listFilms", listFilms);
                HttpSession session = request.getSession();
                session.setAttribute("listFilms", listFilms);
                session.setMaxInactiveInterval(1*60*60); //one hour
                response.sendRedirect("/mainpage");
                session.removeAttribute("possibleHours");
            }
            else {
                ICommand command = controllerHelper.getCommand(request);
                page = command.execute(request, response);
                //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);

                response.sendRedirect(page);
                //dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("something happened");
    }

    private List<Film> listFilms() {
        FilmModel filmModel = new FilmModel();
        return filmModel.getListOfFilms();
    }
}