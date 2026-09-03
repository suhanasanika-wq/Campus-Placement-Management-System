import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudent {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO students (email, password) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "student@gmail.com");
            ps.setString(2, "1234");

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student added successfully!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

