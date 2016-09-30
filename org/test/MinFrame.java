package org.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

public class MinFrame extends JFrame {
	/**
	 * Just a TEST for new design for th GUI
	 */
    public MinFrame() {
        super("Minimal-Frame-Application");
        
        // setup menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
      //  menu.setMnemonicFromAction("F");
        JMenuItem menuItem = new JMenuItem("Exit");
      //  menuItem.setMnemonic("x");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        
        // setup widgets
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
        JScrollPane westPanel = new JScrollPane(new JTree());
        JEditorPane editor = new JEditorPane("text/plain", "Hello World");
        JScrollPane eastPanel = new JScrollPane(editor);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, westPanel,eastPanel);
        splitPane.setDividerLocation(148);
        contentPanel.add(splitPane, BorderLayout.CENTER);
        setContentPane(contentPanel);
        
        // add listeners
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        // show application
        setLocation(32, 32);
        setSize(400, 300);
        show();
    } // end CTor MinFrame
    
    public static void main(String[] args) {
        try {
            // select Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            // start application
            new MinFrame();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    } // end main
    
}