package servlet;

import commands.*;
import entity.Film;
import model.FilmModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public class ControllerHelper {
    private static final String COMMAND = "command";
    private static ControllerHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private ControllerHelper() {
        commands.put("signUp", new SignupCommand());
        commands.put("signIn", new SigninCommand());
        commands.put("signOut", new SignoutCommand());
        commands.put("addFilm", new AddFilmCommand());
        commands.put("removeFilm", new RemoveFilmCommand());
        commands.put("checkAvailableTimeAtHall", new CheckAvailabeTimeAtHallCommand());
        commands.put("addSession", new AddSessionCommand());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter(COMMAND));
        System.out.println(request.getParameter(COMMAND));
        if (command == null) {
            System.out.println(":(");
        }
        return command;
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }


}
