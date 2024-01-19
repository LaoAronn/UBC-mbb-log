package ui;

import model.Player;
import model.Position;
import model.Team;

import javax.swing.*;

public class AddPlayerGUI {

    private static final String POSITION_LIST = "<html><body width='%5s'><h1>Please select a number:</h1>"
            + "<br> 1) Point Guard <br> 2) Shooting Guard <br> 3) Small Forward <br> 4) Power Forward <br> 5) "
            + "Center <br> ";

    public AddPlayerGUI(Team ubcThunderbirds) {
        String playerName = JOptionPane.showInputDialog("Enter player name");
        JOptionPane.showMessageDialog(null, "Player: " + playerName);

        int playerNum = Integer.parseInt(JOptionPane.showInputDialog("Enter " + playerName + "'s jersey number"));
        JOptionPane.showMessageDialog(null, playerName + "'s jersey number: " + playerNum);

        int year = Integer.parseInt(JOptionPane.showInputDialog("Enter " + playerName + "'s Year"));
        JOptionPane.showMessageDialog(null, playerName + "'s Year: " + year);

        int height = Integer.parseInt(JOptionPane.showInputDialog("Enter " + playerName + "'s height"));
        JOptionPane.showMessageDialog(null, playerName + "'s height " + height + "cm");

        boolean playerHealth = getPlayerHealthStatus(JOptionPane.showInputDialog("Is player healthy &"
                + " playable? (Y/N)"));
        JOptionPane.showMessageDialog(null, playerName + ((playerHealth) ? " is Healthy"
                : " is Injured"));

        int posIndex = Integer.parseInt(JOptionPane.showInputDialog(null,
                String.format(POSITION_LIST, 175, 175)));
        Position pos = Position.values()[posIndex - 1];

        if (playerName != null) {
            ubcThunderbirds.addPlayer(new Player(playerNum, playerName, pos, playerHealth, height, year));
        }
    }

    // EFFECTS: Return true if player is healthy to play, false otherwise
    private boolean getPlayerHealthStatus(String response) {
        return response.toLowerCase().contains("y");
    }

}
