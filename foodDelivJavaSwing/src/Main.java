import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // Create a new frame
        JFrame frame = new JFrame("Order Form");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Label and text field for "user"
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(20, 20, 80, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        frame.add(userText);

        // Label and text field for "phone"
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 60, 80, 25);
        frame.add(phoneLabel);

        JTextField phoneText = new JTextField(20);
        phoneText.setBounds(100, 60, 165, 25);
        frame.add(phoneText);

        // Button labeled "Start Order"
        JButton orderButton = new JButton("Start Order");
        orderButton.setBounds(100, 100, 120, 25);
        frame.add(orderButton);

        // Action listener for the button
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userText.getText();
                String phoneNumber = phoneText.getText();

                // Call method to insert the data into MySQL database
                insertIntoDatabase(userName, phoneNumber);

                JOptionPane.showMessageDialog(null, "User: " + userName + "\nPhone: " + phoneNumber);
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    // Method to handle the database connection and insert data
    private static void insertIntoDatabase(String name, String phone) {
        String url = "jdbc:mysql://localhost:3306/userdemo";
        String username = "root";
        String password = "mySQL1234$s-10763";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);
            Statement stat = con.createStatement();

            // Insert user data into the table
            String query = "insert into demotableuser values('" + name + "'," + phone + ");";
            stat.executeUpdate(query);

            // Fetch and print all records from the table
            ResultSet resultSet = stat.executeQuery("select * from demotableuser");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2));
            }

            // Close the connection
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
