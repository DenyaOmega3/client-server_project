package commands;

import entity.Film;
import entity.Guest;
import model.FilmModel;
import model.GuestModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AddFilmCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int runtime = Integer.parseInt(request.getParameter("runtime"));

        FilmModel filmModel = new FilmModel();
        filmModel.setFilm(title,description,runtime);


        return "/mainpage";
    }
}
