package animalcounter;

public abstract class Animal {
    private int count = 0;

    public void resetCount() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    protected void setCount(int value) {
        count = Math.max(0, value);
    }
}
