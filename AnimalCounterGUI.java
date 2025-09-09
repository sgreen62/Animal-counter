package animalCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimalCounterGUI extends JFrame {
    private static final long serialVersionUID = 1L;   // fixes the warning

    // model
    private final Sheep sheep = new Sheep();
    private final Alligator gator = new Alligator(sheep);

    // view components
    private final JLabel welcomeLbl = new JLabel("Animal Counter");
    private final JLabel gatorLbl = new JLabel("Alligators: 0");
    private final JLabel sheepLbl = new JLabel("Sheep: 0");

    private final JButton addGatorBtn = new JButton("Add Alligator");
    private final JButton addSheepBtn = new JButton("Add Sheep");
    private final JButton displayBtn = new JButton("Display Counts");
    private final JButton resetBtn = new JButton("Reset Counts");
    private final JButton exitBtn = new JButton("Exit");

    private final JRadioButton gatorRadio = new JRadioButton("Alligator");
    private final JRadioButton sheepRadio = new JRadioButton("Sheep");

    public AnimalCounterGUI() {
        // basic frame setup
        setTitle("Animal Counter");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // top panel
        JPanel topPanel = new JPanel();
        topPanel.add(welcomeLbl);

        // center panel with labels
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(gatorLbl);
        centerPanel.add(sheepLbl);

        // button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addGatorBtn);
        buttonPanel.add(addSheepBtn);
        buttonPanel.add(displayBtn);
        buttonPanel.add(resetBtn);
        buttonPanel.add(exitBtn);

        // radio panel
        JPanel radioPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        group.add(gatorRadio);
        group.add(sheepRadio);
        radioPanel.add(gatorRadio);
        radioPanel.add(sheepRadio);

        // add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(radioPanel, BorderLayout.WEST);

        // event handler (inner class)
        ActionListener handler = new ButtonHandler();
        addGatorBtn.addActionListener(handler);
        addSheepBtn.addActionListener(handler);
        displayBtn.addActionListener(handler);
        resetBtn.addActionListener(handler);
        exitBtn.addActionListener(handler);
    }

    // inner class for handling events
    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();

            if (src == addGatorBtn) {
                gator.incrementCount();
                updateLabels();
                if (gator.getCount() > sheep.getCount()) {
                    JOptionPane.showMessageDialog(null,
                            "Please add more sheep for the hungry alligators");
                }
            } else if (src == addSheepBtn) {
                sheep.incrementCount();
                updateLabels();
                if (gator.getCount() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "No alligators now so the sheep are safe");
                }
            } else if (src == displayBtn) {
                JOptionPane.showMessageDialog(null,
                        "Alligators: " + gator.getCount() + "\nSheep: " + sheep.getCount());
            } else if (src == resetBtn) {
                if (gatorRadio.isSelected()) {
                    gator.resetCount();
                } else if (sheepRadio.isSelected()) {
                    sheep.resetCount();
                }
                updateLabels();
            } else if (src == exitBtn) {
                System.exit(0);
            }
        }
    }

    private void updateLabels() {
        gatorLbl.setText("Alligators: " + gator.getCount());
        sheepLbl.setText("Sheep: " + sheep.getCount());
    }
}
