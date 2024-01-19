package ui;

import model.Event;
import model.EventLog;
import model.Team;
import model.exception.LogException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainGUI implements ActionListener {

    // Interface fields
    JButton addPlayerButton;
    JButton removePlayerButton;
    JButton displayButton;
    JButton saveButton;
    JButton loadButton;
    JButton eventLogButton;
    JButton quitButton;
    JFrame menuFrame;
    JLabel ubcLogo;
    Container menuContainer;

    private static final String JSON_STORE = "./data/team.json";
    private Team ubcThunderbirds;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructs workroom and runs application
    public MainGUI() throws FileNotFoundException {

        ubcThunderbirds = new Team("UBC Thunderbirds");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        menuFrame = new JFrame("UBC Mens Basketball Team (2022-23)");

        menuContainer = menuFrame.getContentPane();
        menuContainer.setLayout(null);

        // Image set up
        setMenuLogo();

        // Button set up
        listButtons();

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        menuFrame.add(panel, BorderLayout.CENTER);
        menuFrame.setBounds(300, 150, 500, 650);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }

    public void setMenuLogo() {
        // Creating Image
        ImageIcon logo = new ImageIcon(new ImageIcon("data/UBC_mbb.jpeg").getImage().getScaledInstance(
                150, 150, Image.SCALE_DEFAULT));
        ubcLogo = new JLabel(logo);
        ubcLogo.setVisible(true);
        ubcLogo.setBounds(150, 10, 200, 200);
        menuContainer.add(ubcLogo);
    }

    // EFFECTS: Create and displays the buttons for the menu
    public void listButtons() {
        addPlayerButton = new JButton("1) Add Players");
        setUpMenuButton(addPlayerButton, 150, 210, 200, 40);
        addPlayerButton.addActionListener(this);

        removePlayerButton = new JButton("2) Remove Player");
        setUpMenuButton(removePlayerButton, 150, 270, 200, 40);
        removePlayerButton.addActionListener(this);

        displayButton = new JButton("3) Display Players");
        setUpMenuButton(displayButton, 150, 330, 200, 40);
        displayButton.addActionListener(this);

        saveButton = new JButton("4) Save Roster");
        setUpMenuButton(saveButton, 150, 390, 200, 40);
        saveButton.addActionListener(this);

        loadButton = new JButton("5) Load Roster");
        setUpMenuButton(loadButton, 150, 450, 200, 40);
        loadButton.addActionListener(this);

        eventLogButton = new JButton(new PrintLogAction());
        setUpMenuButton(eventLogButton, 150, 510, 200, 40);
        eventLogButton.addActionListener(this);

        quitButton = new JButton("7) QUIT Program");
        setUpMenuButton(quitButton, 150, 570, 200, 40);
        quitButton.addActionListener(this);
    }

    // REQUIRES: Menu button is valid
    // MODIFIES: menuContainer
    // EFFECTS: Sets up button in menu
    public void setUpMenuButton(JButton button, int posX, int posY, int width, int height) {
        button.setBounds(posX, posY, width, height);
        menuContainer.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == addPlayerButton) {
            runAddPlayerButton();
        }

        if (arg0.getSource() == removePlayerButton) {
            runRemovePlayerButton();
        }

        if (arg0.getSource() == displayButton) {
            new DisplayPlayerGUI(ubcThunderbirds);
        }

        if (arg0.getSource() == eventLogButton) {
            new PrintLogAction();
        }

        if (arg0.getSource() == quitButton) {
            getEventLog(EventLog.getInstance());
            System.exit(0);
        }

        checkSaveLoadButtons(arg0);
    }

    // EFFECTS: Run add Player Button
    public void runAddPlayerButton() {
        // IF Team capacity has been reached
        if (ubcThunderbirds.getNumOfPlayers() == ubcThunderbirds.getMaxPlayersInTeam()) {
            // ACTION: Give Error Message
            String errorMessage = "Team Reached Capacity: Cannot add anymore players to team!";
            JOptionPane.showMessageDialog(null, errorMessage);
        } else {
            new AddPlayerGUI(ubcThunderbirds);
        }
    }

    // EFFECTS: Run remove Player Button
    public void runRemovePlayerButton() {
        try {
            RemovePlayerGUI removePlayerDisplay = new RemovePlayerGUI(ubcThunderbirds);
            removePlayerDisplay.openGUI(ubcThunderbirds); // ACTION: remove player from team
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No players to remove. Please add players or load your data");
            // ACTION: Give Error Message
            String errorMessage = "No players to remove. Please add players or load your data";
            JOptionPane.showMessageDialog(null, errorMessage);
        }
    }


    // EFFECTS: Processes Save and Load Buttons
    public void checkSaveLoadButtons(ActionEvent action) {
        if (action.getSource() == saveButton) {
            saveTeam(); // ACTION: SAVE DATA
            menuFrame.setVisible(false);// Set Main Menu Invisible
            JOptionPane.showMessageDialog(menuFrame, "Saved to " + JSON_STORE);
            menuFrame.setVisible(true);// Set Main Menu Invisible
        }

        if (action.getSource() == loadButton) {
            loadTeam(); // ACTION: LOAD DATA
            menuFrame.setVisible(false);// Set Main Menu Invisible
            JOptionPane.showMessageDialog(menuFrame, "Loaded " + JSON_STORE);
            menuFrame.setVisible(true);// Set Main Menu Invisible
        }
    }

    // EFFECTS: Prints the event logs onto the console
    private void getEventLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

    // EFFECTS: saves the team roster to file
    private void saveTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(ubcThunderbirds);
            jsonWriter.close();
            System.out.println("Saved " + ubcThunderbirds.getTeamName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads roster from file
    private void loadTeam() {
        try {
            ubcThunderbirds = jsonReader.read();
            System.out.println("Loaded " + ubcThunderbirds.getTeamName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    /**
     * Represents the action to be taken when the user wants to
     * print the event log.
     */
    private class PrintLogAction extends AbstractAction {
        PrintLogAction() {
            super("6) Print log");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            LogPrinter lp;
            try {
                lp = new EventLogGUI();

                lp.printLog(EventLog.getInstance());
            } catch (LogException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
