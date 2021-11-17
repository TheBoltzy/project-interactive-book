import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

class textEditor extends JFrame implements ActionListener {
    //  Text Area
    JTextArea text;
 
    //  Frame
    JFrame f;

    //  Font Selector
    JComboBox<String> fontSelector;
    JComboBox<String> fontSize;

    int currChapter;

    public void setText() {
        String filePath = "chapters/Chapter " + currChapter + ".txt";
        File fi = new File(filePath);
        
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
    textEditor(int chapter) {
        currChapter = chapter;
        
        //  Create a frame
        f = new JFrame("Text Editor");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
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
 
        //  Create a menu bar
        JMenuBar mb = new JMenuBar();
 
        //  Create a file menu
        JMenu fileMenu = new JMenu("File");
 
        //  Create file menu items
        JMenuItem saveOption = new JMenuItem("Save");
        JMenuItem commentOption = new JMenuItem("Comment");
        JMenuItem backOption = new JMenuItem("Back");
 
        //  Add action listeners
        saveOption.addActionListener(this);
        commentOption.addActionListener(this);
        backOption.addActionListener(this);
 
        fileMenu.add(saveOption);
        fileMenu.add(commentOption);
        fileMenu.add(backOption);
 
        //  Create an edit menu
        JMenu editMenu = new JMenu("Edit");
 
        //  Create edit menu items
        JMenuItem copyOption = new JMenuItem("Copy");
        JMenuItem pasteOption = new JMenuItem("Paste");
        JMenuItem cutOption = new JMenuItem("Cut");
 
        //  Add action listeners
        copyOption.addActionListener(this);
        pasteOption.addActionListener(this);
        cutOption.addActionListener(this);
 
        editMenu.add(copyOption);
        editMenu.add(pasteOption);
        editMenu.add(cutOption);

        //  Create font selector
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontSelector = new JComboBox<String>(fonts);
        fontSelector.setSelectedItem("Georgia");
        fontSelector.addActionListener(this);

        String[] sizes = {"8", "10", "12", "14", "16", "18", "20", "22", "24", "36", "48", "60"};
        fontSize = new JComboBox<String>(sizes);
        fontSize.setSelectedIndex(3);
        fontSize.addActionListener(this);
 
        mb.add(fileMenu);
        mb.add(editMenu);
        mb.add(fontSelector);
        mb.add(fontSize);
 
        //  Add everything to the frame
        f.setJMenuBar(mb);
        f.getContentPane().add(scrollPane);
        f.setSize(1000, 700);
        f.setLocationRelativeTo(null);
        f.setVisible(true);;
    }
 
    //  If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
 
        if (s.equals("Copy")) {
            text.copy();

        } else if (s.equals("Paste")) {
            text.paste();

        } else if (s.equals("Cut")) {
            text.cut();

        } else if (s.equals("Save")) {
            String filePath = "chapters/Chapter " + currChapter + ".txt";
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
        } else if (s.equals("Comment")) {
            comment com = new comment(currChapter);
        } else if (s.equals("Back")) {
            //  close text editor and return to chapter selection screen
            f.setVisible(false);
            f.dispose();

            chapterPicker chapter = new chapterPicker();
        } else {
            String s2 = (String) fontSelector.getSelectedItem(); 
            int size = Integer.parseInt((String) fontSize.getSelectedItem());
            text.setFont(new Font(s2, Font.PLAIN, size));
        }
    }


    //  Main class
    public static void main(String args[])
    {
        textEditor e = new textEditor(6);
    }
}