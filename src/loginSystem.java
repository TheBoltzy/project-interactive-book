import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class loginSystem extends JFrame implements ActionListener {
 
    //  Frame
    JFrame f;

    JTextField user;
    JPasswordField pass;

    //  Hard-coded usernames and passwords; this will be
    //  replaced by database access in the future
    String[] usernames = {"u", "username1", "username2", "user3", "thisUserNAME"};
    String[] passwords = {"p", "password1", "password2", "pass3", "aPaaassWORD"};
 
    //  Constructor
    loginSystem() {
        //  Create a frame
        f = new JFrame("Login");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        try {
            //  Set the look and feel to system (windows)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }

        JPanel panel = new JPanel(new FlowLayout());
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        //  Create labels
        JLabel userL = new JLabel("Username: ");
        JLabel passL = new JLabel("Password: ");

        //  Create fields
        user = new JTextField(10);
        pass = new JPasswordField(10);

        //  Create login button
        JButton login = new JButton("Login");
        login.addActionListener(this);

        //  Add fields and labels
        panel.add(userL);
        panel.add(user);
        panel.add(passL);
        panel.add(pass);
        panel.add(login);

        //  Add everything to the frame
        f.add(panel, BorderLayout.CENTER);
        f.setSize(375, 100);
        f.setLocationRelativeTo(null);
        f.setVisible(true);;
    }
 
    //  If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s1 = user.getText();
        String s2 = new String(pass.getPassword());

        // JDBC connection to database
        
        try {
		Connection connect = DriverManager.getConnection("jdbc:mysql://68.205.83.101:3306/interactivebook", s1, s2);
		Class.forName("com.mysql.cj.jdbc.Driver");
		f.setVisible(false);
        	f.dispose();
        	chapterPicker selector = new chapterPicker();
	} catch (SQLException except) {
		JOptionPane.showMessageDialog(null, except.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
	} catch (ClassNotFoundException classnotfound) {
		JOptionPane.showMessageDialog(loginSystem.this, "Error! Driver not found");
	}
    }


    public static void main(String args[])
    {
        loginSystem e = new loginSystem();
    }
}