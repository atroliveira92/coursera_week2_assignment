import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;

    private int head = 0;
    private int tail = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    private class ListIterator implements Iterator<Item> {
        private int i = head;

        ListIterator() {
            StdRandom.setSeed(size());
        }

        public boolean hasNext() {
            return i < tail;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = items[getRandomIndex()];
            i++;
            return item;
        }
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return tail - head == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return tail - head;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("cannot add null value");
        }
        if (tail == items.length) {
            resize(2 * items.length);
        }

        items[tail++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("the list is empty");
        }
        int n = getRandomIndex();
        Item item = items[n];

        //System.out.println("n=" + n + " head=" + head + " tail=" + tail + " size " + size());

        if (n > size() / 2) {
            if (n < size() - 1) {
                for (int i = n; i < size(); i++) {
                    Item val = items[i + 1];
                    items[i + 1] = items[i];
                    items[i] = val;
                }
            }
            items[--tail] = null;
        } else {
            if (n > 0) {
                for (int i = n; i > head; i--) {
                    Item val = items[i - 1];
                    items[i - 1] = items[i];
                    items[i] = val;
                }
            }
            items[head++] = null;
        }

        if (size() > 0 && size() == items.length / 4) {
            resize(items.length / 2);
        }

        if (isEmpty()) {
            items = (Item[]) new Object[1];
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
        int i = StdRandom.uniform(head, tail);
        return i;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = head, j = 0; i < tail; i++, j++) {
            copy[j] = items[i];
        }
        items = copy;
        tail = size();
        head = 0;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        int k = 3;
        if (args.length > 0) {
            k = Integer.parseInt(args[0]);
        }

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

        System.out.println("Random dequeue: " + randomizedQueue.dequeue());
        System.out.println("Random dequeue: " + randomizedQueue.dequeue());


        System.out.println("Size " + randomizedQueue.size());

        Iterator<Integer> iterator = randomizedQueue.iterator();
        int i = 0;
        while (i < k && iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
            i++;
        }

    }

    private static void printValues(RandomizedQueue<Integer> randomizedQueue) {
        for (Integer i : randomizedQueue) {
            System.out.println("Item: " + i);
        }
    }

}
