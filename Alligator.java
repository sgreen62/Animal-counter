package animalcounter;

public class Alligator extends Animal implements Countable {
    // reference so gators can “eat” sheep per spec
    private final Sheep sheepRef;

    public Alligator(Sheep sheepRef) {
        this.sheepRef = sheepRef; // ok to pass in; count still starts at 0
        resetCount();
    }

    // add 1 alligator; if any sheep exist, remove 1
    @Override
    public void incrementCount() {
        setCount(getCount() + 1);
        if (sheepRef.getCount() > 0) {
            sheepRef.setCount(sheepRef.getCount() - 1);
        }
        // messages are shown by the GUI after each action
    }
}
