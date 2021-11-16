import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;

class chapterPicker extends JFrame implements ActionListener, ListSelectionListener {
 
    //  Global swing components
    JFrame f;
    JList<String> list;
    JButton button;

    //  Generates chapter array based on the number we pass in
    private String[] createChapters(int num) {
        String chapterArr[] = new String[num];
        
        for(int i = 1; i <= num; i++) {
            chapterArr[i-1] = "Chapter " + i;
        }

        return chapterArr;
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

        //  Generate chapter array
        String[] chapters = createChapters(6);

        //  Create chapter list
        list = new JList<String>(chapters);
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
        f.setSize(60, 300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);;
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
    
    //  If open is pressed
    public void actionPerformed(ActionEvent e) {
        int chpt = list.getSelectedIndex() + 1;
        f.setVisible(false);
        f.dispose();

        //  Pass in selected chapter
        textEditor edit = new textEditor(chpt);
    }


    public static void main(String args[])
    {
        chapterPicker e = new chapterPicker();
    }
}