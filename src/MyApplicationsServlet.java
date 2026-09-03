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

@WebServlet("/my-applications")
public class MyApplicationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();

            int studentId = 1;

            String sql =
                    "SELECT c.company_name, c.job_role, c.package, " +
                    "a.application_date, a.status " +
                    "FROM applications a " +
                    "JOIN companies c ON a.company_id = c.id " +
                    "WHERE a.student_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>My Applications</title>");
            out.println("</head>");

            out.println("<body style='font-family: Arial; background:#f4f6f9;'>");

            out.println("<div style='width:80%; margin:40px auto;'>");
            out.println("<h2>My Applications</h2>");

            boolean found = false;

            while (rs.next()) {

                found = true;

                out.println("<div style='background:white; padding:20px; margin-bottom:15px; border-radius:8px;'>");

                out.println("<h3>" + rs.getString("company_name") + "</h3>");

                out.println("<p><strong>Role:</strong> "
                        + rs.getString("job_role") + "</p>");

                out.println("<p><strong>Package:</strong> "
                        + rs.getString("package") + "</p>");

                out.println("<p><strong>Application Date:</strong> "
                        + rs.getDate("application_date") + "</p>");

                out.println("<p><strong>Status:</strong> "
                        + rs.getString("status") + "</p>");

                out.println("</div>");
            }

            if (!found) {
                out.println("<p>You have not applied to any companies yet.</p>");
            }

            out.println("<a href='student-dashboard.html'>Back to Dashboard</a>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

            out.println("<h2>Unable to load applications.</h2>");
        }
    }
}
