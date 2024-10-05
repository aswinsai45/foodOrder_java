import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // Create the JFrame
        JFrame frame = new JFrame("Enter Name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Create a panel for content
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(50, 50, 50));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Create a label for the text field
        JLabel label = new JLabel("Enter Name:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the text field to enter the name
        JTextField nameField = new JTextField(20);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the button to save the name
        JButton saveButton = new JButton("Save Name");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 16));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setBackground(new Color(70, 130, 180));
        saveButton.setForeground(Color.WHITE);

        // Add action listener to the save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Save the name to the database
                    saveNameToDatabase(name);
                    JOptionPane.showMessageDialog(frame, "Name saved successfully!");
                }
            }
        });

        // Add components to the panel
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between label and text field
        panel.add(nameField);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between text field and button
        panel.add(saveButton);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Method to save the name into the PostgreSQL database
    private static void saveNameToDatabase(String name) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Database connection
            String url = "jdbc:postgresql://localhost:5432/postgres"; // Replace with your actual database URL
            String user = "postgres"; // Replace with your actual username
            String password = "password123"; // Replace with your actual password

            conn = DriverManager.getConnection(url, user, password);

            // SQL INSERT query to save the name
            String sql = "INSERT INTO demoTable1 VALUES id"; // Replace 'your_table_name' with your actual table name
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            // Execute the query
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
