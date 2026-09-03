import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-status")
public class UpdateStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String applicationIdText = request.getParameter("application_id");
        String status = request.getParameter("status");

        try {
            int applicationId = Integer.parseInt(applicationIdText);

            Connection con = DBConnection.getConnection();

            String sql =
                    "UPDATE applications " +
                    "SET status = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, applicationId);

            ps.executeUpdate();

            ps.close();
            con.close();

            response.sendRedirect("view-applicants");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Unable to update application status.</h2>");
        }
    }
}
