package miptsbertechpdris;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter write = resp.getWriter();

    String username = req.getParameter("username");
    String password = req.getParameter("password");

    if (username == null || username.equals("")) {
      write.println("<h1 style=\"color:red;\">Bad username</h1>");
      RequestDispatcher rd = req.getRequestDispatcher("/register.html");
      rd.include(req, resp);
      return;
    }
    if (password == null || password.equals("")) {
      write.println("<h1 style=\"color:red;\">Bad password</h1>");
      RequestDispatcher rd = req.getRequestDispatcher("/register.html");
      rd.include(req, resp);
      return;
    }

    if (Storage.usernameExists(username)) {
      if (Storage.checkPassword(username, password)) {
        req.setAttribute("username", username);
        RequestDispatcher rd = req.getRequestDispatcher("/success.jsp");
        rd.include(req, resp);
      } else {
        write.println("<h1 style=\"color:red;\">Wrong password</h1>");
        RequestDispatcher rd = req.getRequestDispatcher("/login.html");
        rd.include(req, resp);
      }
    } else {
      write.println("<h1 style=\"color:red;\">Wrong user</h1>");
      RequestDispatcher rd = req.getRequestDispatcher("/login.html");
      rd.include(req, resp);
    }
  }
}
