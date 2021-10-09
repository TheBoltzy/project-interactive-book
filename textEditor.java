import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.text.*;

class textEditor extends JFrame implements ActionListener {
    // Text component
    JTextArea text;
 
    // Frame
    JFrame f;

    // Font Selector
    JComboBox fontSelector;
    JComboBox fontSize;
 
    // Constructor
    textEditor() {
        // Create a frame
        f = new JFrame("Text Editor");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        try {
            //Set the look and feel to system (windows)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }
 
        // Create text area
        text = new JTextArea();
        text.setFont(new Font("Georgia", Font.PLAIN, 14));

        // Create a scroll pane
        JScrollPane scrollPane = new JScrollPane(text);
 
        // Create a menu bar
        JMenuBar mb = new JMenuBar();
 
        // Create a file menu
        JMenu fileMenu = new JMenu("File");
 
        // Create file menu items
        JMenuItem newOption = new JMenuItem("New");
        JMenuItem openOption = new JMenuItem("Open");
        JMenuItem saveOption = new JMenuItem("Save");
 
        // Add action listeners
        newOption.addActionListener(this);
        openOption.addActionListener(this);
        saveOption.addActionListener(this);
 
        fileMenu.add(newOption);
        fileMenu.add(openOption);
        fileMenu.add(saveOption);
 
        // Create an edit menu
        JMenu editMenu = new JMenu("Edit");
 
        // Create edit menu items
        JMenuItem copyOption = new JMenuItem("Copy");
        JMenuItem pasteOption = new JMenuItem("Paste");
        JMenuItem cutOption = new JMenuItem("Cut");
 
        // Add action listeners
        copyOption.addActionListener(this);
        pasteOption.addActionListener(this);
        cutOption.addActionListener(this);
 
        editMenu.add(copyOption);
        editMenu.add(pasteOption);
        editMenu.add(cutOption);

        // Create font selector
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontSelector = new JComboBox(fonts);
        fontSelector.setSelectedItem("Georgia");
        fontSelector.addActionListener(this);

        String[] sizes = {"8", "10", "12", "14", "16", "18", "20", "22", "24", "36", "48", "60"};
        fontSize = new JComboBox(sizes);
        fontSize.setSelectedIndex(3);
        fontSize.addActionListener(this);
 
        mb.add(fileMenu);
        mb.add(editMenu);
        mb.add(fontSelector);
        mb.add(fontSize);
        //mb.add(closeOption);
 
        // Add everything to the frame
        f.setJMenuBar(mb);
        f.getContentPane().add(scrollPane);
        f.setSize(1000, 700);
        f.setVisible(true);;
    }
 
    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
 
        if (s.equals("Copy")) {
            text.copy();

        } else if (s.equals("Paste")) {
            text.paste();

        } else if (s.equals("Cut")) {
            text.cut();

        } else if (s.equals("Save")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");
 
            // Invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);
 
            if (r == JFileChooser.APPROVE_OPTION) {
 
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());
 
                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);
 
                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);
 
                    // Write
                    w.write(text.getText());
 
                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(f, "Operation Cancelled");
            }
        } else if (s.equals("Open")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");
 
            // Invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);
 
            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());
 
                try {
                    // Initialize str1
                    String str1 = "", str2 = "";
 
                    // File reader
                    FileReader fr = new FileReader(fi);
 
                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);
 
                    // Set up buffer
                    str2 = br.readLine();
 
                    // Take the input from the file
                    while ((str1 = br.readLine()) != null) {
                        str2 = str2 + "\n" + str1;
                    }
 
                    // Set the text
                    text.setText(str2);

                    //Close the reader to prevent leakage
                    br.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(f, "Operation Cancelled");
            }
        } else if (s.equals("New")) {
            text.setText("");
        } else {
            String s2 = (String) fontSelector.getSelectedItem(); 
            int size = Integer.parseInt((String) fontSize.getSelectedItem());
            text.setFont(new Font(s2, Font.PLAIN, size));
        }
    }


    // Main class
    public static void main(String args[])
    {
        textEditor e = new textEditor();
    }
}