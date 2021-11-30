import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.*;
import java.util.Arrays;
import java.awt.event.*;
import java.sql.*;

class bookPicker extends JFrame implements ActionListener, ListSelectionListener {

    // Global swing components
    JFrame f;
    DefaultListModel<String> listModel;
    JList<String> list;
    JButton button;
    int bookCount;
    int bookNum;
    String tempName = null;

    private Repository repo;

    // Generates book array based on the number we pass in
    private DefaultListModel<String> createBooks() {
        // bookNum = 1;
        // // GRAB CHOSEN BOOK NUMBER HERE

        // File folder = new File("../lib/books");
        // File[] files = folder.listFiles();
        // bookCount = folder.list().length;
        // // grabs the list of books and stores how many we have in bookCount

        // String s;
        // int[] nums = new int[bookCount];

        // for (int i = 0; i < bookCount; i++) {
        // s = files[i].getName();
        // s = s.substring(4, s.length());
        // // removes the .txt from the filenames and puts book numbers into nums[]

        // nums[i] = Integer.parseInt(s);
        // }

        // Arrays.sort(nums);

        return repo.getBooksFromUser(0);
    }
    // Constructor
    bookPicker(Repository repo) {
        // initialize repo
        this.repo = repo;

        // Create a frame
        f = new JFrame("Choose a Book");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            // Set the look and feel to system (windows)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        // Creating menu bar
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem newBook = new JMenuItem("Create Book");
        JMenuItem logOut = new JMenuItem("Log Out");

        // Adding action listeners to menu items
        newBook.addActionListener(this);
        logOut.addActionListener(this);

        // Adding items to menu bar
        menu.add(newBook);
        menu.add(logOut);
        mb.add(menu);

        // Generate book array
        listModel = createBooks();

        // Create book list
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(this);

        // Set renderer for list to center the books
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Create scroll pane
        JScrollPane listScroller = new JScrollPane(list);

        // Set button
        button = new JButton("See Chapters");
        button.addActionListener(this);
        button.setEnabled(false);

        // Create panel from f
        Container panel = f.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(listScroller);
        panel.add(button);

        // Add everything to the frame
        f.pack();
        f.setJMenuBar(mb);
        f.setSize(150, 300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // Disables open button if nothing is selected
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                // No selection, disable open button
                button.setEnabled(false);
            } else {
                // Selection, enable open button
                button.setEnabled(true);
            }
        }
    }

    // If open is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Create Book")) {
            String name = (String) JOptionPane.showInputDialog(f, "", "Enter Book Name", JOptionPane.PLAIN_MESSAGE);

            if (name != null && name.length() > 0) {
                tempName = name;
            } else {
                name = null;
            }

            if (name == null) { // they didn't enter a book name
                JOptionPane.showMessageDialog(f, "No name entered.\nBook not created.");

            } else {
                bookCount++;
                listModel.addElement(name);
                File newBook = new File("../lib/books/book" + bookCount);
                newBook.mkdir();
                // makes a directory for the new book

                File newChapters = new File("../lib/books/book" + bookCount + "/chapters");
                File newComments = new File("../lib/books/book" + bookCount + "/comments");
                newChapters.mkdir();
                newComments.mkdir();
                // makes directories for both chapters and comments

                File nameFile = new File(newBook + ".txt");

                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(nameFile, false);

                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);

                    // Write
                    w.write(name);

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                } // writes the entered name to the text file
            }

        } else if (s.equals("Log Out")) {
            f.setVisible(false);
            f.dispose();
            loginSystem login = new loginSystem();

        } else {
            int bk = repo.getBooksId(list.getSelectedValue());
            f.setVisible(false);
            f.dispose();

            // Pass in selected book
            chapterPicker chap = new chapterPicker(bk);
        }
    }

    public static void main(String args[]) {
        bookPicker e = new bookPicker(new Repository());
    }
}
