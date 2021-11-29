import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.sql.*;

class comment extends JFrame {
    //  Text Area
    JTextArea text;
 
    //  Frame
    JFrame f;

    //  Font Selector
    JComboBox<String> fontSelector;
    JComboBox<String> fontSize;

    int currChapter;
    int bookNum = 1;
    //GRAB HERE

    public void setText() {
        String filePath = "../lib/books/book" + bookNum + "/comments/comment" + currChapter + ".txt";
        File fi = new File(filePath);

        try {
            fi.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        } //    Try to create a new comment file if one has not already been made
        
        try {
            //  Initialize str1
            String str1 = "", str2 = "";

            //  File reader
            FileReader fr = new FileReader(fi);

            //  Buffered reader
            BufferedReader br = new BufferedReader(fr);

            //  Set up buffer
            str2 = br.readLine();

            //  Take the input from the file
            while ((str1 = br.readLine()) != null) {
                str2 = str2 + "\n" + str1;
            }

            //  Set the text
            text.setText(str2);

            //  Close the reader to prevent leakage
            br.close();
        } catch (Exception evt) {
            JOptionPane.showMessageDialog(f, evt.getMessage());
        }
    }
 
    //  Constructor
    comment(int chapter) {
        currChapter = chapter;
        
        //  Create a frame
        f = new JFrame("Comment");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //  Saves content on close
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String filePath = "../lib/books/book" + bookNum + "/comments/comment" + chapter + ".txt";
                File fi = new File(filePath);
            
            try {
                //  Create a file writer
                FileWriter wr = new FileWriter(fi, false);

                //  Create buffered writer to write
                BufferedWriter w = new BufferedWriter(wr);

                //  Write
                w.write(text.getText());

                w.flush();
                w.close();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
            
            }
        
        });
 
        try {
            //  Set the look and feel to system (windows)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }
 
        //  Create text area
        text = new JTextArea();
        text.setFont(new Font("Georgia", Font.PLAIN, 14));
        setText();

        //  Create a scroll pane
        JScrollPane scrollPane = new JScrollPane(text);
 
        //  Add everything to the frame
        f.getContentPane().add(scrollPane);
        f.setSize(550, 450);
        f.setLocation(1000, 300);
        f.setVisible(true);
    }


    //  Main class
    public static void main(String args[])
    {
        comment e = new comment(6);
    }
}
