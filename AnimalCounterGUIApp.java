package animalcounter;

import javax.swing.SwingUtilities;

public class AnimalCounterGUIApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnimalCounterGUI().setVisible(true));
    }
}
