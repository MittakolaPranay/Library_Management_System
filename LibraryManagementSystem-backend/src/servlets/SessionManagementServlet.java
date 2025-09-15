package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class SessionManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        JSONObject responseObject = new JSONObject();


        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");

        try {

            HttpSession session = req.getSession(false);


            if(session != null &&
                    session.getAttribute("userID") != null &&
                    session.getAttribute("userName") != null &&
                    session.getAttribute("userEmail") != null &&
                    session.getAttribute("userRole") != null
            ) {
                responseObject.put("status",true);
                responseObject.put("userID",session.getAttribute("userID"));
                responseObject.put("userEmail",session.getAttribute("userEmail"));
                responseObject.put("userName",session.getAttribute("userName"));
                responseObject.put("userRole",session.getAttribute("userRole"));

            } else {
                responseObject.put("status",false);
                responseObject.put("message","Your session has expired. Please log in again to continue.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        writer.print(responseObject.toString());
        writer.flush();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");
    }
}



