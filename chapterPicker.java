import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.*;
import java.util.Arrays;
import java.awt.event.*;
import java.sql.*;

class chapterPicker extends JFrame implements ActionListener, ListSelectionListener {
 
    //  Global swing components
    JFrame f;
    JList<String> list;
    JButton button;
    int chapterCount;
    DefaultListModel<String> listModel;

    //  Generates chapter array based on the number we pass in
    private DefaultListModel<String> createChapters() {
        File folder = new File("chapters");
        File[] files = folder.listFiles();
        chapterCount = folder.list().length;
        String s;
        int[] nums = new int[chapterCount];

        for(int i = 0; i < chapterCount; i++) {
            s = files[i].getName();
            s = s.substring(8, s.length() - 4);
            nums[i] = Integer.parseInt(s);
        }

        Arrays.sort(nums);

        DefaultListModel<String> model = new DefaultListModel<String>();
        
        for(int i = 0; i < chapterCount; i++) {
            model.addElement("Chapter " + nums[i]);
        }

        return model;
    }

    //  Constructor
    chapterPicker() {
        //  Create a frame
        f = new JFrame("Choose a Chapter");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        try {
            //  Set the look and feel to system (windows)
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }

        //  Creating menu bar
        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem newChapter = new JMenuItem("New Chapter");
        JMenuItem deleteChapter = new JMenuItem("Delete Chapter");
        JMenuItem logOut = new JMenuItem("Log Out");

        // Adding action listeners to menu items
        newChapter.addActionListener(this);
        deleteChapter.addActionListener(this);
        logOut.addActionListener(this);

        //  Adding items to menu bar
        menu.add(newChapter);
        menu.add(deleteChapter);
        menu.add(logOut);
        mb.add(menu);

        //  Generate chapter array
        listModel = createChapters();

        //  Create chapter list
        list = new JList<String>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(this);

        //  Set renderer for list to center the chapters
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        //  Create scroll pane
        JScrollPane listScroller = new JScrollPane(list);

        //  Set button
        button = new JButton("Open");
        button.addActionListener(this);
        button.setEnabled(false);

        //  Create panel from f
        Container panel = f.getContentPane();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(listScroller);
        panel.add(button);

        //  Add everything to the frame
        f.pack();
        f.setJMenuBar(mb);
        f.setSize(150, 300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
 
    //  Disables open button if nothing is selected
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                //  No selection, disable open button
                button.setEnabled(false);
            } else {
                //  Selection, enable open button
                button.setEnabled(true);
            }
        }
    }

    public void addChapter() {
        try {
            chapterCount++;
            listModel.addElement("Chapter " + chapterCount);
            File newChapter = new File("chapters\\Chapter " + chapterCount + ".txt");
            newChapter.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            chapterCount--;
        }
    }
    
    //  If open is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
 
        if (s.equals("New Chapter")) {
            addChapter();

        } else if (s.equals("Delete Chapter")) {
            //  Not being used now as deleting specific chapters
            //  causes problems, but I want to keep the code
            /*int selected = list.getSelectedIndex();
            String name = listModel.elementAt(selected);
            File chap = new File("chapters\\" + name + ".txt"); */

            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + listModel.elementAt(chapterCount-1) + "?", "CONFIRM DELETION",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

            if (confirm == 0) { //   Yes, delete
                File chap = new File("chapters\\" + listModel.elementAt(chapterCount-1) + ".txt");
                chap.delete();
                listModel.remove(chapterCount-1);
                chapterCount--;
            }
            //  Otherwise there's nothing to do

        } else if (s.equals("Log Out")) {
            f.setVisible(false);
            f.dispose();
            loginSystem login = new loginSystem();

        } else {
            int chpt = list.getSelectedIndex() + 1;
            f.setVisible(false);
            f.dispose();

            //  Pass in selected chapter
            textEditor edit = new textEditor(chpt);          
        }
    }


    public static void main(String args[])
    {
        chapterPicker e = new chapterPicker();
    }
}
