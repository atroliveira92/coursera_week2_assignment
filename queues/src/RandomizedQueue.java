import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    private class ListIterator implements Iterator<Item> {
        private int i;
        private int[] randomArray;

        ListIterator() {
            i = 0;
            createRandomList();
        }

        private void createRandomList() {
            randomArray = new int[size];

            int j;
            for (j = 0; j < size; j++) {
                randomArray[j] = j;
            }

            int r, tmp;
            for (j = 1; j < size; j++) {
                r = StdRandom.uniform(j);
                tmp = randomArray[r];
                randomArray[r] = randomArray[j];
                randomArray[j] = tmp;
            }
        }

        public boolean hasNext() {
            return i < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[randomArray[i++]];
        }
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("cannot add null value");
        }
        if (size == items.length) {
            resize(2 * items.length);
        }

        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("the list is empty");
        }
        int n = getRandomIndex();
        Item item = items[n];

        items[n] = items[--size];
        items[size] = null;

        if (size() > 0 && size() == items.length / 4) {
            resize(items.length / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (!isEmpty()) {
            return items[getRandomIndex()];
        }
        return null;
    }

    private int getRandomIndex() {
        return StdRandom.uniform(size);
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        randomizedQueue.enqueue(0);
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        System.out.println("Random dequeue: " + randomizedQueue.dequeue());
        randomizedQueue.enqueue(4);
        randomizedQueue.enqueue(5);
        System.out.println("Random dequeue: " + randomizedQueue.dequeue());
        randomizedQueue.enqueue(6);
        randomizedQueue.enqueue(7);
        randomizedQueue.enqueue(8);
        randomizedQueue.enqueue(9);

        System.out.println("Random dequeue: " + randomizedQueue.sample());

        System.out.println("Random dequeue: " + randomizedQueue.dequeue());
        System.out.println("Random dequeue: " + randomizedQueue.dequeue());

        System.out.println("Size " + randomizedQueue.size());
        for (Integer i : randomizedQueue) {
            System.out.println("Item: " + i);
        }
    }

    private static void printValues(RandomizedQueue<Integer> randomizedQueue) {

    }

}
