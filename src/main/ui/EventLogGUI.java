package ui;

import model.Event;
import model.EventLog;

import java.awt.*;

import javax.swing.*;


/**
 * Represents a screen printer for printing event log to screen.
 */
public class EventLogGUI implements LogPrinter {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JPanel contentPane;
    private JTextArea logArea;
    private JFrame logFrame;

    /**
     * Constructor sets up window in which log will be printed on screen
     */
    public EventLogGUI() {

        logFrame = new JFrame("Event Log");
        logFrame.setBounds(100, 100, 200, 200);
        logFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logFrame.setVisible(true);

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setPreferredSize(new Dimension(250, 300));
        logFrame.add(scrollPane);

        logArea = new JTextArea();
        logArea.setEditable(false);

        contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(0, 1));
        contentPane.add(logArea, BorderLayout.LINE_START);
        logFrame.setContentPane(contentPane);
    }

    @Override
    public void printLog(EventLog el) {

        for (Event next : el) {
            logArea.setText(logArea.getText() + next.toString() + "\n\n");
        }

    }

}
