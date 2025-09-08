package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

public class CheckConnection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        try(
                Connection connection = DBConnection.getConnector();
                ) {
            if(connection != null) {
                writer.print("<h1>Data base connection success full</h1>");
                writer.flush();
            }
        } catch (Exception e) {
            writer.print("<h1>connection failed</h1>");
            writer.flush();
            e.printStackTrace();
        }
    }
}
