package commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

public interface ICommand {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
