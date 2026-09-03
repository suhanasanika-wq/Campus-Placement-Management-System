import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String companyIdText = request.getParameter("company_id");

        response.setContentType("text/html");

        try {
            int companyId = Integer.parseInt(companyIdText);

            // Our test student has ID 1
            int studentId = 1;

            Connection con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO applications " +
                    "(student_id, company_id, application_date, status) " +
                    "VALUES (?, ?, CURDATE(), 'Applied')";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentId);
            ps.setInt(2, companyId);

            int rows = ps.executeUpdate();

            PrintWriter out = response.getWriter();

            if (rows > 0) {
                out.println("<html>");
                out.println("<body>");
                out.println("<h2>Application submitted successfully!</h2>");
                out.println("<a href='available-companies.html'>Back to Companies</a>");
                out.println("</body>");
                out.println("</html>");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();

            PrintWriter out = response.getWriter();
            out.println("<h2>Unable to submit application.</h2>");
            out.println("<a href='available-companies.html'>Go Back</a>");
        }
    }
}
