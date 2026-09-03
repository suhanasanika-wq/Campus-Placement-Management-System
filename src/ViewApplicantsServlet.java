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

@WebServlet("/view-applicants")
public class ViewApplicantsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();

            // TCS company ID = 2
            int companyId = 2;

            String sql =
                    "SELECT a.id AS application_id, " +
                    "s.name, s.email, s.branch, s.cgpa, s.skills, " +
                    "a.application_date, a.status " +
                    "FROM applications a " +
                    "JOIN students s ON a.student_id = s.id " +
                    "WHERE a.company_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, companyId);

            ResultSet rs = ps.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Applicants</title>");

            out.println("<style>");
            out.println("body { font-family: Arial; background:#f4f6f9; margin:0; }");
            out.println(".container { width:85%; margin:40px auto; }");
            out.println(".applicant { background:white; padding:20px; margin-bottom:15px; border-radius:8px; box-shadow:0 2px 8px rgba(0,0,0,0.1); }");
            out.println(".accept { padding:10px 15px; background:#28a745; color:white; text-decoration:none; border-radius:5px; margin-right:10px; }");
            out.println(".reject { padding:10px 15px; background:#dc3545; color:white; text-decoration:none; border-radius:5px; }");
            out.println(".back { display:inline-block; margin-top:20px; padding:10px 15px; background:#007bff; color:white; text-decoration:none; border-radius:5px; }");
            out.println("</style>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container'>");
            out.println("<h2>TCS Applicants</h2>");

            boolean found = false;

            while (rs.next()) {
                found = true;

                int applicationId = rs.getInt("application_id");

                out.println("<div class='applicant'>");

                out.println("<h3>" + rs.getString("name") + "</h3>");

                out.println("<p><strong>Email:</strong> "
                        + rs.getString("email") + "</p>");

                out.println("<p><strong>Branch:</strong> "
                        + rs.getString("branch") + "</p>");

                out.println("<p><strong>CGPA:</strong> "
                        + rs.getString("cgpa") + "</p>");

                out.println("<p><strong>Skills:</strong> "
                        + rs.getString("skills") + "</p>");

                out.println("<p><strong>Application Date:</strong> "
                        + rs.getDate("application_date") + "</p>");

                out.println("<p><strong>Status:</strong> "
                        + rs.getString("status") + "</p>");

                out.println("<a class='accept' href='update-status?application_id="
                        + applicationId + "&status=Accepted'>Accept</a>");

                out.println("<a class='reject' href='update-status?application_id="
                        + applicationId + "&status=Rejected'>Reject</a>");

                out.println("</div>");
            }

            if (!found) {
                out.println("<p>No students have applied yet.</p>");
            }

            out.println("<a class='back' href='company-dashboard.html'>Back to Dashboard</a>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Unable to load applicants.</h2>");
        }
    }
}