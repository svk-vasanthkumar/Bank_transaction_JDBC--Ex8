import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String jdbcURL = "jdbc:mysql://localhost:3306/bank";
        String dbUser = "root";
        String dbPassword = "svk@636807"; // XAMPP default

        try {
            // 1. Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish the connection
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // 3. Create a statement
            Statement statement = connection.createStatement();

            // 4. Query to get customer transactions
            String query = "SELECT * FROM transactions WHERE customer_id = 101";

            // 5. Execute query
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Transaction Details for Customer ID 101:");

            // 6. Read result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int customerId = resultSet.getInt("customer_id");
                double amount = resultSet.getDouble("transaction_amount");
                String date = resultSet.getString("transaction_date");
                String description = resultSet.getString("description");

                System.out.println("ID: " + id +
                        ", Customer ID: " + customerId +
                        ", Amount: " + amount +
                        ", Date: " + date +
                        ", Description: " + description);
            }

            // 7. Close connection
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
