import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class loginSystem extends JFrame implements ActionListener {
 
    // Frame
    JFrame f;

    JTextField user;
    JPasswordField pass;

    String[] usernames = {"username1", "username2", "user3", "thisUserNAME"};
    String[] passwords = {"password1", "password2", "pass3", "aPaaassWORD"};
 
    // Constructor
    loginSystem() {
        // Create a frame
        f = new JFrame("Login");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        try {
            //Set the look and feel to system (windows)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }

        JPanel panel = new JPanel(new FlowLayout());
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        // Create labels
        JLabel userL = new JLabel("Username: ");
        JLabel passL = new JLabel("Password: ");

        // Create fields
        user = new JTextField(10);
        pass = new JPasswordField(10);

        // Create login button
        JButton login = new JButton("Login");
        login.addActionListener(this);

        // Add fields and labels
        panel.add(userL);
        panel.add(user);
        panel.add(passL);
        panel.add(pass);
        panel.add(login);

        // Add everything to the frame
        f.add(panel, BorderLayout.CENTER);
        //f.add(login, BorderLayout.SOUTH);
        f.setSize(375, 100);
        f.setLocationRelativeTo(null);
        f.setVisible(true);;
    }
 
    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s1 = user.getText();
        String s2 = new String(pass.getPassword());

        int userIndex = -1;

        // Compares input username to ones in database
        for(int i = 0; i < usernames.length; i++) {
            if (s1.equals(usernames[i])) {
                userIndex = i;
                break;
            }
        }

        // Checks if we found a username match
        if (userIndex == -1) {
            JOptionPane.showMessageDialog(f, "No such username exists.", "Incorrect Username", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // If we reached this point, the username is a match
        if (!s2.equals(passwords[userIndex])) {
            JOptionPane.showMessageDialog(f, "Incorrect password.", "Incorrect Password", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //If we reached this point, both the username and password are correct
        System.out.println("Logging in " + s1 + " now...");
        f.setVisible(false);
        f.dispose();
        textEditor edit = new textEditor();
    }


    public static void main(String args[])
    {
        loginSystem e = new loginSystem();
    }
}