package ui;

import model.Player;
import model.Team;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RemovePlayerGUI extends JPanel implements ListSelectionListener, ActionListener {
    private JList<Player> list;
    private DefaultListModel<Player> listModel;
    private Team referenceTeam;
    private static final String REMOVE = "Remove";
    private JFrame mainFrame = new JFrame("Remove players tab");
    private JButton removePlayerButton;

    public RemovePlayerGUI(Team team) {
        super(new BorderLayout());

        referenceTeam = team;
        listModel = new DefaultListModel<>();
        list = new JList<>();
        fillModelList(listModel, team); // Fill element with players

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        initaliseButtons();

        String name = listModel.getElementAt(list.getSelectedIndex()).toString();

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(removePlayerButton); // Remove Player Button
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPane.add(Box.createHorizontalStrut(15));
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // EFFECT: Adds player list to the DefaultListModel
    public void fillModelList(DefaultListModel<Player> model, Team team) {
        List<Player> players = team.getPlayers();

        for (Player player : players) {
            model.addElement(player);
        }
    }

    // EFFECTS: Initalising the two buttons
    public void initaliseButtons() {
        removePlayerButton = new JButton(REMOVE);
        removePlayerButton.setActionCommand(REMOVE);
        removePlayerButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == removePlayerButton) {
            //This method can be called only if there's a valid selection
            int index = list.getSelectedIndex();
            referenceTeam.removePlayer(listModel.get(index));
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removePlayerButton.setEnabled(false);
            } else { //Select an index.
                if (index == listModel.getSize()) {
                    index--; //removed item in last position
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }

        }

    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                removePlayerButton.setEnabled(false); //No selection, disable fire button.
            } else {
                removePlayerButton.setEnabled(true); //Selection, enable the fire button.
            }
        }
    }

    public void openGUI(Team team) {
        //Create and set up the window.
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(200, 300));

        //Create and set up the content pane.
        JComponent newContentPane = new RemovePlayerGUI(team);
        newContentPane.setOpaque(true); //content panes must be opaque
        mainFrame.setContentPane(newContentPane);

        //Display the window.
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

}
