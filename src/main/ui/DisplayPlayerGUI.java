package ui;

import model.Player;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DisplayPlayerGUI {

    JFrame frameDisplayPlayer;
    JLabel nameLabel;
    JLabel jerseyLabel;
    JLabel posLabel;
    JLabel ageLabel;
    JLabel heightLabel;
    JLabel healthLabel;

    public DisplayPlayerGUI(Team ubcThunderbirds) {
        frameDisplayPlayer = new JFrame("UBC Thunderbirds Roster");
        JList<Player> list = new JList<>();
        DefaultListModel<Player> model = new DefaultListModel<>();
        fillModelList(model, ubcThunderbirds); // Fill element with players

        JPanel panel = new JPanel();
        JSplitPane splitPane = new JSplitPane();
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(250, 300));

        list.setModel(model);
        splitPane.setLeftComponent(scrollPane);
        panel.setLayout(new GridLayout(0, 1));

        // List set up
        displayPlayerInfo(list);
        panel.add(nameLabel);
        panel.add(jerseyLabel);
        panel.add(posLabel);
        panel.add(ageLabel);
        panel.add(heightLabel);
        panel.add(healthLabel);

        splitPane.setRightComponent(panel);
        frameDisplayPlayer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameDisplayPlayer.add(splitPane);
        frameDisplayPlayer.setBounds(100, 100, 1000, 300);
        //frameDisplayPlayer.setLocationRelativeTo(null);
        frameDisplayPlayer.setVisible(true);
    }

    // EFFECTS: Creates the player fields displayable
    public void displayPlayerInfo(JList<Player> list) {
        nameLabel = new JLabel("", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        jerseyLabel = new JLabel("", SwingConstants.CENTER);
        jerseyLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        posLabel = new JLabel("", SwingConstants.CENTER);
        posLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        ageLabel = new JLabel("", SwingConstants.CENTER);
        ageLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        heightLabel = new JLabel("", SwingConstants.CENTER);
        heightLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        healthLabel = new JLabel("", SwingConstants.CENTER);
        healthLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        list.getSelectionModel().addListSelectionListener(e -> {
            Player player = list.getSelectedValue();
            nameLabel.setText("Name: " + player.getPlayerName() + "");
            jerseyLabel.setText("Jersey Number: " + player.getJerseyNum());
            posLabel.setText("Position: " + player.getPosition());
            ageLabel.setText("Year: " + player.getYear());
            heightLabel.setText("Height: " + player.getHeight() + "cm");
            healthLabel.setText("Health Status: " + getHealthStatus(player));
        });
    }

    // EFFECTS: returns a message if player is healthy to play based on their isActive field
    private String getHealthStatus(Player p) {
        if (p.getIsActive()) { // If player is healthy
            return "Healthy & Ready to play";
        } else {
            return "Injured & Unavailable to play";
        }
    }

    // EFFECT: Adds player list to the DefaultListModel
    public void fillModelList(DefaultListModel<Player> model, Team ubcThunderbirds) {
        List<Player> players = ubcThunderbirds.getPlayers();

        for (Player player : players) {
            model.addElement(player);
        }
    }
}
