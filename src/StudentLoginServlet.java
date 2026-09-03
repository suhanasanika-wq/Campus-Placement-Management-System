import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student-login")
public class StudentLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get email and password from student-login.html
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html");

        try {
            // Connect to MySQL
            Connection con = DBConnection.getConnection();

            // Check whether email and password exist
            String sql = "SELECT * FROM students WHERE email = ? AND password = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            // Check login
            if (rs.next()) {

                // Correct login -> open dashboard
                response.sendRedirect("student-dashboard.html");

            } else {

                // Wrong login
                PrintWriter out = response.getWriter();

                out.println("<html>");
                out.println("<body>");
                out.println("<h2>Invalid email or password!</h2>");
                out.println("<a href='student-login.html'>Try Again</a>");
                out.println("</body>");
                out.println("</html>");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            PrintWriter out = response.getWriter();
            out.println("<h2>Database Error!</h2>");
            out.println("<p>Please try again.</p>");
        }
    }
}