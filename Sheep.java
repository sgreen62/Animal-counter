package animalcounter;

public class Sheep extends Animal implements Countable {

    public Sheep() {
        resetCount();
    }

    // add 2 sheep
    @Override
    public void incrementCount() {
        setCount(getCount() + 2);
        // messages are shown by the GUI after each action
    }
}
