package animalcounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalCounterGUI extends JFrame {

    // model
    private final Sheep sheep = new Sheep();
    private final Alligator gator = new Alligator(sheep);

    // view
    private final JLabel welcomeLbl = new JLabel("Animal Counter");
    private final JLabel gatorLbl = new JLabel("Alligators: 0");
    private final JLabel sheepLbl = new JLabel("Sheep: 0");

    private final JButton addGatorBtn = new JButton("Add Alligator (+1)");
    private final JButton addSheepBtn = new JButton("Add Sheep (+2)");
    private final JButton displayBtn = new JButton("Display Counts");
    private final JButton resetBtn = new JButton("Reset Counts");
    private final JButton exitBtn = new JButton("Exit");

    private final JRadioButton rbGator = new JRadioButton("Alligator", true);
    private final JRadioButton rbSheep = new JRadioButton("Sheep");

    public AnimalCounterGUI() {
        super("Week 9 - Animal Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 220);
        setLocationRelativeTo(null);

        // radio group
        ButtonGroup group = new ButtonGroup();
        group.add(rbGator);
        group.add(rbSheep);

        // layout: simple BorderLayout with a compact grid center
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(welcomeLbl);

        JPanel center = new JPanel(new GridLayout(3, 2, 6, 6));
        center.add(gatorLbl);
        center.add(sheepLbl);
        center.add(addGatorBtn);
        center.add(addSheepBtn);
        center.add(displayBtn);
        center.add(resetBtn);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(new JLabel("Reset:"));
        bottom.add(rbGator);
        bottom.add(rbSheep);
        bottom.add(exitBtn);

        setLayout(new BorderLayout(8, 8));
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // single handler for all buttons
        ButtonHandler handler = new ButtonHandler();
        addGatorBtn.addActionListener(handler);
        addSheepBtn.addActionListener(handler);
        displayBtn.addActionListener(handler);
        resetBtn.addActionListener(handler);
        exitBtn.addActionListener(handler);

        // initial display
        updateLabels();
    }

    private void updateLabels() {
        gatorLbl.setText("Alligators: " + gator.getCount());
        sheepLbl.setText("Sheep: " + sheep.getCount());
    }

    // show messages required by the spec
    private void checkMessages() {
        if (gator.getCount() > sheep.getCount()) {
            JOptionPane.showMessageDialog(this,
                    "Please add more sheep for the hungry alligators",
                    "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
        if (gator.getCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "No alligators now so the sheep are safe",
                    "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();

            if (src == addGatorBtn) {
                gator.incrementCount();    // +1 gator and -1 sheep if any
                updateLabels();
                checkMessages();
            } else if (src == addSheepBtn) {
                sheep.incrementCount();     // +2 sheep
                updateLabels();
                checkMessages();
            } else if (src == displayBtn) {
                JOptionPane.showMessageDialog(AnimalCounterGUI.this,
                        "Alligators: " + gator.getCount() + "\nSheep: " + sheep.getCount(),
                        "Current Counts", JOptionPane.PLAIN_MESSAGE);
                checkMessages();
            } else if (src == resetBtn) {
                if (rbGator.isSelected()) {
                    gator.resetCount();
                } else if (rbSheep.isSelected()) {
                    sheep.resetCount();
                }
                updateLabels();
                checkMessages();
            } else if (src == exitBtn) {
                dispose();
            }
        }
    }
}
