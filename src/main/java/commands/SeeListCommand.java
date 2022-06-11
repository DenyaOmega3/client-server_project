package commands;

import entity.Film;
import model.FilmModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SeeListCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Film> listFilms = listFilms();
        request.setAttribute("listFilms", listFilms);

        return "/mainpage";
    }

    private List<Film> listFilms() {
        FilmModel filmModel = new FilmModel();
        return filmModel.getListOfFilms();
    }
}
