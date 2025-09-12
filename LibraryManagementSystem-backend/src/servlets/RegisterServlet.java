package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import models.UserDAO;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        JSONObject responseObject = new JSONObject();


        res.setHeader("Access-Control-Allow-Origin","http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        User user = new User(name,email,password,role);
        UserDAO userDAO = new UserDAO();

        try {
            if (userDAO.registerUser(user)) {
                responseObject.put("status", true);
                responseObject.put("message", "User registered successfully!");
            } else {
                responseObject.put("status", false);
                responseObject.put("message", "Registration failed. Try again.");
            }
        } catch (Exception e) {
            responseObject.put("status", false);
            responseObject.put("message", "Server error: " + e.getMessage());
        }

        writer.print(responseObject.toString());
        writer.close();
    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "content-type");
        res.setHeader("Access-Control-Allow-Credentials", "true");
    }


}
