package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;
import it.unibo.oop.lab.mvcio.SimpleGUI;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private final JFrame frame = new JFrame();
    public static void main(final String... args) {
        final SimpleGUIWithFileChooser test = new SimpleGUIWithFileChooser();
    }
    public SimpleGUIWithFileChooser() {

        final Controller ioController = new Controller();
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        final JPanel mainPanel = new JPanel();
        final JTextArea textArea = new JTextArea();
        final JButton button = new JButton("Save");
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(textArea, BorderLayout.CENTER);
        mainPanel.add(button, BorderLayout.SOUTH);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                final int n = JOptionPane.showConfirmDialog(mainPanel, "Salvare ?", "Conferma", JOptionPane.YES_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    try {
                        ioController.writeOnFile(textArea.getText());
                    } catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(mainPanel, "File non trovato KTM");
                        e1.printStackTrace();
                    }
                }
            }
        });
        // Adding the new Elements
        final JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout());
        final JTextField textF = new JTextField();
        textF.setEditable(false);
        textF.setText(ioController.getPath());
        final JButton browse = new JButton("Browse ..");
        secondPanel.add(textF, BorderLayout.CENTER);
        secondPanel.add(browse, BorderLayout.LINE_END);
        mainPanel.add(secondPanel, BorderLayout.NORTH);
        browse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                final int n = fc.showSaveDialog(fc);
                if (n == JFileChooser.APPROVE_OPTION) {
                    System.out.println(fc.getSelectedFile());
                    ioController.setFile(fc.getSelectedFile().toString()); 
                    textF.setText(ioController.getPath());
                } 
            }
        });

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}