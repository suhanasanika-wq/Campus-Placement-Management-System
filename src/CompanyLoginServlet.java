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

@WebServlet("/company-login")
public class CompanyLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String companyName = request.getParameter("company_name");
        String password = request.getParameter("password");

        response.setContentType("text/html");

        try {
            Connection con = DBConnection.getConnection();

            String sql =
                "SELECT * FROM companies WHERE company_name = ? AND password = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, companyName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                response.sendRedirect("company-dashboard.html");

            } else {

                PrintWriter out = response.getWriter();

                out.println("<html>");
                out.println("<body>");
                out.println("<h2>Invalid company name or password!</h2>");
                out.println("<a href='company-login.html'>Try Again</a>");
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
