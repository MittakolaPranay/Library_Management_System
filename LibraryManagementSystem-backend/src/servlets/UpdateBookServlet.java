package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Book;
import models.BookDAO;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UpdateBookServlet extends HttpServlet {

    private static final String UPLOAD_FOLDER = "D:\\PRANAY\\Projects\\LibraryImages\\";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        JSONObject responseObject = new JSONObject();


        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");

        try {
            String stringId = req.getParameter("id");
            int id = Integer.parseInt(stringId);
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String isbn = req.getParameter("isbn");
            String stringCopies = req.getParameter("copies");
            int copies = Integer.parseInt(stringCopies);
            String stringAvailable = req.getParameter("available");
            int available = Integer.parseInt(stringAvailable);
            Part image = req.getPart("image");

            if(title == null || author == null || isbn == null || copies <= 0 || available <= 0 || image == null) {
                responseObject.put("status", "error");
                responseObject.put("message", "All fields are required");
                writer.print(responseObject.toString());
                return;
            }

            String fileName = image.getSubmittedFileName();
            InputStream fileContent = image.getInputStream();

                    File upload = new File(UPLOAD_FOLDER);
            if(!upload.exists()) upload.mkdirs();

            File file = new File(upload,fileName);

            Files.copy(fileContent,file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "/LibraryImages/"+fileName;

            Book book = new Book(id,title,author,isbn,copies,available,imageUrl);
            BookDAO bookDAO = new BookDAO();
            boolean success = bookDAO.updateBook(book);

            if(success) {
                responseObject.put("status",true);
                responseObject.put("message","updating book is successful");
            }else {
                responseObject.put("status",false);
                responseObject.put("message","failed to update book");
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
